package com.dang.dnolja.review.model.service.impl;

import com.dang.dnolja.daily.model.mapper.DailyMapper;
import com.dang.dnolja.global.Exception.InvalidAuthorityException;
import com.dang.dnolja.plan.model.mapper.PlanMapper;
import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import com.dang.dnolja.review.model.mapper.ReviewMapper;
import com.dang.dnolja.review.model.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final PlanMapper planMapper;
    private final ReviewMapper reviewMapper;
    private final DailyMapper dailyMapper;



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
}
