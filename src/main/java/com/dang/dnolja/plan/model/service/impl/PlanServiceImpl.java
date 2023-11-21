package com.dang.dnolja.plan.model.service.impl;

import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.model.service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    @Transactional
    public void create(Long userId, PlanPostRequest planPostRequest) {



    }
}
