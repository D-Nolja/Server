package com.dang.dnolja.plan.controller;

import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/plan")
@AllArgsConstructor
public class PlanController {


    @PostMapping()
    public CommonResponse<String> create(Authentication auth, @RequestBody PlanPostRequest planPostRequest){

        log.debug("[] user :: {} , request :: {}", auth.getPrincipal(), planPostRequest);


        return new CommonResponse<>("성공하였습니다.");

    }
}
