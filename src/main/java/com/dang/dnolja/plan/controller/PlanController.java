package com.dang.dnolja.plan.controller;

import com.dang.dnolja.auth.details.CustomUserDetail;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanModifyRequest;
import com.dang.dnolja.plan.controller.dto.request.PlanPostRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanDetailDto;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import com.dang.dnolja.plan.model.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
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

    @GetMapping()
    public CommonResponse<PlanListDto> getMyList(Authentication auth, @ModelAttribute @Valid  PlanListRequest request,  BindingResult bindingResult){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        PlanListDto result = planService.getMyList(user.getUser().getId(), request);
        log.debug("[PlanController getMyList] userId :: {} , request :: {}", user.getUser().getId(), request);
        return new CommonResponse<>(result);
    }



    @GetMapping("/{id}")
    public CommonResponse<PlanDetailDto> getDetail(@PathVariable("id") Long planId) throws NotFoundException {
        log.debug("planId {}", planId);
        PlanDetailDto result = planService.getDetail(planId);
        
        return new CommonResponse<>(result);
    }

    @DeleteMapping("{id}")
    public CommonResponse<String> remove(@PathVariable("id") long planId, Authentication auth){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        planService.delete(planId, user.getUser().getId());

        return new CommonResponse<>(String.format("%s가 삭제되었습니다.", planId));
    }

    @PutMapping()
    public CommonResponse<String> modify(@RequestBody PlanModifyRequest request, Authentication auth){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();

        planService.modify(request.getPlanId(), user.getUser().getId(), request);

        return new CommonResponse<>("수정완료");
    }

}
