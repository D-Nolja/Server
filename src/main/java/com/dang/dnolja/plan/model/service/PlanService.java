package com.dang.dnolja.plan.model.service;

import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanDetailDto;
import com.dang.dnolja.plan.controller.dto.response.PlanItemDto;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface PlanService {



    PlanListDto getMyList(Long userId, PlanListRequest request);

    PlanListDto getList(PlanListRequest request);

    PlanDetailDto getDetail(Long planId) throws NotFoundException;

    void create(Long userId, PlanPostRequest planPostRequest);

    void delete(long planId, long userId);





}
