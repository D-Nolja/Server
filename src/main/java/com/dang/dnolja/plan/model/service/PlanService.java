package com.dang.dnolja.plan.model.service;

import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;

public interface PlanService {

    void create(Long userId, PlanPostRequest planPostRequest);



}
