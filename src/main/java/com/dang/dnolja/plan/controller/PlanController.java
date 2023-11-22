package com.dang.dnolja.plan.controller;

import com.dang.dnolja.auth.details.CustomUserDetail;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanDetailDto;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import com.dang.dnolja.plan.model.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("/plan")
@AllArgsConstructor
public class PlanController {

    private final PlanService planService;


    @PostMapping()
    public CommonResponse<String> create(Authentication auth, @RequestBody @Valid PlanPostRequest request,  BindingResult bindingResult){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        log.debug("[PlanController create] userId :: {} , request :: {}", user.getUser().getId(), request);
        planService.create(user.getUser().getId(), request);

        return new CommonResponse<>("성공하였습니다.");

    }

    @GetMapping("/my")
    public CommonResponse<PlanListDto> getMyList(Authentication auth, @ModelAttribute @Valid  PlanListRequest request,  BindingResult bindingResult){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        PlanListDto result = planService.getMyList(user.getUser().getId(), request);
        log.debug("[PlanController getMyList] userId :: {} , request :: {}", user.getUser().getId(), request);
        return new CommonResponse<>(result);
    }

    @GetMapping()
    public CommonResponse<PlanListDto> getList(@ModelAttribute @Valid PlanListRequest request ,  BindingResult bindingResult){
        PlanListDto result = planService.getList(request);
        log.debug("[PlanController getList] request :: {}",request);
        return new CommonResponse<>(result);
    }

    @GetMapping("/{id}")
    public CommonResponse<PlanDetailDto> getDetail(@PathVariable("id") Long planId){
        log.debug("planId {}", planId);
        PlanDetailDto result = planService.getDetail(planId);
        
        return new CommonResponse<>(result);
    }

}
