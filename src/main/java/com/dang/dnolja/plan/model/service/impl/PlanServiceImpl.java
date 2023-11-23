package com.dang.dnolja.plan.model.service.impl;

import com.dang.dnolja.daily.controller.dto.response.DailyListResponse;
import com.dang.dnolja.daily.model.dto.DailyDetailDto;
import  com.dang.dnolja.daily.model.mapper.DailyMapper;
import com.dang.dnolja.global.Exception.InvalidAuthorityException;
import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanModifyRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanDetailDto;
import com.dang.dnolja.plan.controller.dto.response.PlanItemDto;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import com.dang.dnolja.plan.model.dto.Plan;
import com.dang.dnolja.plan.model.mapper.PlanMapper;
import com.dang.dnolja.plan.model.service.PlanService;
import com.dang.dnolja.review.model.mapper.ReviewMapper;
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
public class PlanServiceImpl implements PlanService {

    private final PlanMapper planMapper;
    private final DailyMapper dailyMapper;
    private final ReviewMapper reviewMapper;


    @Override
    public PlanListDto getMyList(Long userId, PlanListRequest request) {
        Map<String, Object> searchParams = getSearchParams(request);
        searchParams.put("userId", userId);
        List<PlanItemDto> result = planMapper.getList(searchParams);
        int totalPageCount = planMapper.getTotalPlanCount(searchParams);
        return PlanListDto.builder()
                .totalPageCount(totalPageCount)
                .planList(result)
                .currentPage((int) searchParams.get("currentPage"))
                .build();
    }

    @Override
    public PlanListDto getList(PlanListRequest request) {
        Map<String, Object> searchParams = getSearchParams(request);
        List<PlanItemDto> result = planMapper.getList(searchParams);
        int totalPageCount = planMapper.getTotalPlanCount(searchParams);
        return PlanListDto.builder()
                .totalPageCount(totalPageCount)
                .planList(result)
                .currentPage((int) searchParams.get("currentPage"))
                .build();
    }


    @Override
    public PlanDetailDto getDetail(Long planId) throws NotFoundException {
        Plan planResult = planMapper.findById(planId);
        if(planResult == null) throw new NotFoundException(String.format("%s인 plan이 존재하지 않습니다.", planId));

        int maxDayNum = dailyMapper.getMaxDayNum(planId);
        Map<String, Object> params = new HashMap<>();
        params.put("planId", planId);

        ArrayList<DailyListResponse> dailyList = new ArrayList<>();

        for(int day=1; day<=maxDayNum; day++){
            params.put("day", day);

            List<DailyDetailDto>result= dailyMapper.getDailyDetailList(params);



            dailyList.add(
                    DailyListResponse.builder()
                    .dailyPlan(result)
                    .day(day)
                    .build());

        }
        log.debug("dailyList :: {}",  dailyList);

        return PlanDetailDto.builder()
                .planId(planId)
                .userId(planResult.getUserId())
                .title(planResult.getTitle())
                .start(planResult.getStart())
                .end(planResult.getEnd())
                .createdAt(planResult.getCreatedAt())
                .modifiedAt(planResult.getModifiedAt())

                .planDetails(dailyList).build();
    }

    @Override
    @Transactional
    public void create(Long userId, PlanPostRequest request) {

        Map<String, Object> planParams = getPlanParams(userId, request);
        log.debug(" planParams :: {}", planParams);
        planMapper.createPlan(planParams);
        //plan을 생성하고 id값을 가져온다.
        long planId = planMapper.getLastPk();
        //request에 있는 plans를 순회한다.
        int dayNum = 1;


        for(int[] dayPlan : request.getPlans()){
            log.debug("dayNum :: {}", dayNum);
            Map<String, Object> dailyParams = getDailyParams(planId, dayNum);
            log.debug("dailyParams :: {}", dailyParams);
            dailyMapper.insertDaily(dailyParams);
            long dailyId = dailyMapper.getLastPk();
            log.debug("{}일 계획 생성 성공 ", dayNum);
            int order = 1;
            for(int spotId : dayPlan){
               Map<String, Object> reviewParams = getReviewParams(dailyId, spotId, order);
                log.debug("reviewParams :: {}", reviewParams);
               reviewMapper.insertDailyToSpot(reviewParams);
               order++;
           }

           dayNum ++;
        }
            //daily를 생성한다. + plan ID 같이 넣어준다.
    }

    @Override
    @Transactional
    public void delete(long planId, long userId) {
        //PLAN ID에서 userId 일치하는지 확인
        long authorId = planMapper.getUserIdById(planId);
        //select user_id from plan where plan_id =1;

        if(authorId != userId) throw new InvalidAuthorityException("계획 삭제 권한이 없습니다.");

        //plan_id로 모든 daily_id를 가져온다.
        List<Long> dailyIds = dailyMapper.getDailyIdsByPlanId(planId);
        //select daily_id from daily where plan_id = 1;

        log.debug("{}", dailyIds);
        //가져온 daily_id 순회
        for(Long dailyId: dailyIds){
            reviewMapper.deleteByDailyId(dailyId);
            //DELETE FROM Review
            //WHERE daily_id=데이터값
            dailyMapper.deleteById(dailyId);
            //DELETE FROM Daily
            //WHERE daily_id=데이터값
        }

        planMapper.deleteById(planId);
        //DELETE FROM Plan
        //WHERE plan_id=데이터값



        //해당 daily_id를 갖는 review 전부 삭제
        //해당 daily 삭제
        //plan 삭제

    }


//    {
//        "title" : "여행계획 1",
//        "plans" : [ [701,732,753,744,1],
//                    [685,706,747,742,731],
//                    [710,732,732,743]
//                        ],
//        "start" : "시작날짜",
//        "end" : "종료날짜"
//    }
    @Override
    @Transactional
    public void modify(long planId, long userId, PlanModifyRequest request){
        //PLAN ID에서 userId 일치하는지 확인
        long authorId = planMapper.getUserIdById(planId);
        //select user_id from plan where plan_id =1;

        if(authorId != userId) throw new InvalidAuthorityException("계획 수정 권한이 없습니다.");

        //plan_id로 모든 daily_id를 가져온다.
        List<Long> dailyIds = dailyMapper.getDailyIdsByPlanId(planId);
        //select daily_id from daily where plan_id = 1;

        //가져온 daily_id 순회
        for(Long dailyId: dailyIds){
            reviewMapper.deleteByDailyId(dailyId);
            //DELETE FROM Review
            //WHERE daily_id=데이터값
            dailyMapper.deleteById(dailyId);
            //DELETE FROM Daily
            //WHERE daily_id=데이터값
        }

        //plan 업데이트
        planMapper.modify(request);

        //review 생성하기
        int dayNum = 1;
        for(int[] dayPlan : request.getPlans()){
            log.debug("dayNum :: {}", dayNum);
            Map<String, Object> dailyParams = getDailyParams(planId, dayNum);
            log.debug("dailyParams :: {}", dailyParams);
            dailyMapper.insertDaily(dailyParams);
            long dailyId = dailyMapper.getLastPk();
            log.debug("{}일 계획 생성 성공 ", dayNum);
            int order = 1;
            for(int spotId : dayPlan){
                Map<String, Object> reviewParams = getReviewParams(dailyId, spotId, order);
                log.debug("reviewParams :: {}", reviewParams);
                reviewMapper.insertDailyToSpot(reviewParams);
                order++;
            }
            dayNum ++;
        }
    }


    private Map<String, Object> getReviewParams(long dailyId, int spotId, int order) {
        Map<String, Object> reviewParams = new HashMap<>();
        reviewParams.put("dailyId", dailyId);
        reviewParams.put("spotId", spotId);
        reviewParams.put("order", order);

        return reviewParams;
    }

    private Map<String, Object> getDailyParams(long planId, int dayNum) {
        Map<String, Object> dailyParams = new HashMap<>();

        dailyParams.put("planId", planId);
        dailyParams.put("dayNum", dayNum);

        return dailyParams;
    }

    private Map<String, Object> getPlanParams(long userId, PlanPostRequest request){
        Map<String, Object> planParams = new HashMap<>();
        planParams.put("title", request.getTitle());
        planParams.put("userId", userId);
        planParams.put("start", request.getStart());
        planParams.put("end", request.getEnd());

        return planParams;
    }

    private Map<String, Object> getSearchParams(PlanListRequest request) {
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
