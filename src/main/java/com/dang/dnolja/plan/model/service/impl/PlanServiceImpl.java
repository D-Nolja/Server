package com.dang.dnolja.plan.model.service.impl;

import com.dang.dnolja.daily.controller.dto.response.DailyListResponse;
import com.dang.dnolja.daily.model.dto.DailyDetailDto;
import  com.dang.dnolja.daily.model.mapper.DailyMapper;
import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanDetailDto;
import com.dang.dnolja.plan.controller.dto.response.PlanItemDto;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import com.dang.dnolja.plan.model.mapper.PlanMapper;
import com.dang.dnolja.plan.model.service.PlanService;
import com.dang.dnolja.review.model.mapper.ReviewMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public PlanDetailDto getDetail(Long planId){
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
                .planDetails(dailyList).build();
    }

    @Override
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
