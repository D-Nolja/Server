package com.dang.dnolja.review.model.service.impl;

import com.dang.dnolja.daily.model.mapper.DailyMapper;
import com.dang.dnolja.global.Exception.InvalidAuthorityException;
import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.review.controller.dto.request.*;
import com.dang.dnolja.review.controller.dto.response.ReviewListResponse;
import com.dang.dnolja.review.model.dto.PlanReview;
import com.dang.dnolja.plan.model.mapper.PlanMapper;
import com.dang.dnolja.review.controller.dto.response.ReviewResponse;
import com.dang.dnolja.review.model.dto.ReviewDetailDto;
import com.dang.dnolja.review.model.dto.ReviewList;
import com.dang.dnolja.review.model.dto.ReviewMetaData;
import com.dang.dnolja.review.model.mapper.ReviewMapper;
import com.dang.dnolja.review.model.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final PlanMapper planMapper;
    private final ReviewMapper reviewMapper;
    private final DailyMapper dailyMapper;

    @Override
    public ReviewListResponse getList(ReviewListRequest request) {
        Map<String, Object> searchParams = getSearchParams(request);

        List<ReviewMetaData> result  = reviewMapper.getList(searchParams);
        int totalPageCount = reviewMapper.getTotalReviewCount(searchParams);



        return ReviewListResponse.builder()
                .reviews(result)
                .currentPage((int)searchParams.get("currentPage"))
                .totalPageCount(totalPageCount)
                .build();
    }

    @Override
    public void delete(long planId, long userId) {
        long authorId = planMapper.getUserIdById(planId);

        if(authorId != userId) throw new InvalidAuthorityException("삭제 권한이 없습니다.");


        //plan 칼럼에서 review_title, review_img, review_contents 없애기
        planMapper.reset(planId);
        //daily id 찾아오기
        List<Long> dailyIds = dailyMapper.getDailyIdsByPlanId(planId);



        //daily id 순회 돌면서
            //review 테이블 contents img 없애기
        for(long dailyId : dailyIds){
            reviewMapper.resetByDailyId(dailyId);
        }

    }

    @Override
    public void modify(ReviewModifyRequest request, long userId) {
        long authorId = reviewMapper.getUserId(request.getReviewId());

        if(authorId != userId) throw new InvalidAuthorityException("수정 권한이 없습니다.");

        //review의 contents, img 수정하기

        reviewMapper.modify(request);
    }

    @Override
    public void modifyMain(MainReviewModifyRequest request, long userId) {

        long authorId = planMapper.getUserIdById(request.getPlanId());

        if(authorId != userId) throw new InvalidAuthorityException("수정 권한이 없습니다.");


        //plan의 review_title, review_img, review_contents 업데이트
        planMapper.modifyReview(request);
    }

    @Override
    public void createReviewItem(ReviewItemRequest request, long userId) {

        //reviewId로 userId 조회
        long authorId = reviewMapper.getUserId(request.getReviewId());

        //userId와 authentication 일치 확인
        if(authorId != userId){
            throw new InvalidAuthorityException(String.format("%s review에 접근할 수 없습니다.", request.getReviewId()));
        }

        //reviewMapper에서 수정하기
        reviewMapper.updateReviewItem(request);

    }

    @Override
    @Transactional
    public void createReview(ReviewPostRequest request, long userId) {
        List<ReviewItemRequest> reviewRequests = request.getReviews();

        reviewMapper.updateReviewMetaData(request);

        for(ReviewItemRequest reviewRequest : reviewRequests){
            createReviewItem(reviewRequest, userId);
        }
    }

    @Override
    public ReviewResponse getDetail(Long planId) throws NotFoundException {
        PlanReview planResult = reviewMapper.findPlanReviewById(planId);
        if(planResult == null) throw new NotFoundException(String.format("%s인 plan이 존재하지 않습니다.", planId));

        int maxDayNum = dailyMapper.getMaxDayNum(planId);
        Map<String, Object> params = new HashMap<>();
        params.put("planId", planId);

        List<ReviewList> dailyList = new ArrayList<>();

        for(int day=1; day<=maxDayNum; day++){
            params.put("day", day);

            List<ReviewDetailDto>result= reviewMapper.getReviewDetailList(params);



            dailyList.add(
                    ReviewList.builder()
                            .reviewDetails(result)
                            .day(day)
                            .build());

        }
        log.debug("dailyList :: {}",  dailyList);

        return ReviewResponse
                .builder()
                .planId(planId)
                .userId(planResult.getUserId())
                .userName(planResult.getUserName())
                .title(planResult.getMainTitle())
                .mainContent(planResult.getMainContents())
                .mainImg(planResult.getMainImg())
                .start(planResult.getStart())
                .end(planResult.getEnd())
                .createdAt(planResult.getCreatedAt())
                .modifiedAt(planResult.getModifiedAt())
                .reviewList(dailyList)
                .build();
    }

    private Map<String, Object> getSearchParams(ReviewListRequest request) {
        Map<String, Object> params = new HashMap<>();
        log.debug("[LocationServiceImpl findLocation] request :: {}", request);

        params.put("keyWord", request.getKeyword() == null ? "" : request.getKeyword());
        int pageNo = request.getCurrentPage() == null ? 1: request.getCurrentPage();
        int sizePerPage = request.getSizePerPage()== null ? 5 : request.getSizePerPage();
        int start = pageNo * sizePerPage - sizePerPage;
        params.put("start", start);
        params.put("sizePerPage", sizePerPage);
        params.put("currentPage", pageNo);

        return params;
    }

}
