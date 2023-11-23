package com.dang.dnolja.plan.model.service;

import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanModifyRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanDetailDto;
import com.dang.dnolja.plan.controller.dto.response.PlanItemDto;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlanService {



    PlanListDto getMyList(Long userId, PlanListRequest request);

    PlanListDto getList(PlanListRequest request);

    PlanDetailDto getDetail(Long planId) throws NotFoundException;

    void create(Long userId, PlanPostRequest planPostRequest);

    void delete(long planId, long userId);


    //    {
    //        "title" : "여행계획 1",
    //        "plans" : [ [701,732,753,744,1],
    //                    [685,706,747,742,731],
    //                    [710,732,732,743]
    //                        ],
    //        "start" : "시작날짜",
    //        "end" : "종료날짜"
    //    }
        @Transactional
        void modify(long planId, long userId, PlanModifyRequest request);
}
