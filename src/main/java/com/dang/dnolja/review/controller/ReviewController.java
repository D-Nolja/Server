package com.dang.dnolja.review.controller;

import com.dang.dnolja.auth.details.CustomUserDetail;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.plan.controller.dto.request.PlanListRequest;
import com.dang.dnolja.plan.controller.dto.response.PlanListDto;
import com.dang.dnolja.plan.model.service.PlanService;
import com.dang.dnolja.review.controller.dto.request.*;
import com.dang.dnolja.review.controller.dto.response.ReviewListResponse;
import com.dang.dnolja.review.controller.dto.response.ReviewResponse;
import com.dang.dnolja.review.model.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController()
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //임시저장
    @PostMapping("/item")
    public CommonResponse<String> createReviewItem(@RequestBody ReviewItemRequest request, Authentication auth){

        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        reviewService.createReviewItem(request, user.getUser().getId());

        return new CommonResponse<>(String.format("%s가 임시저장되었습니다.", request.getReviewId()));
    }

    @PostMapping
    public CommonResponse<String> create(@RequestBody ReviewPostRequest request, Authentication auth){
        log.info("request ::{}", request);
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();
        reviewService.createReview(request, user.getUser().getId());
        return new CommonResponse<>("성공했습니다.");
    }


    @GetMapping("/{id}")
    public CommonResponse<ReviewResponse> getDetail(@PathVariable("id") long planId) throws NotFoundException {
        ReviewResponse result = reviewService.getDetail(planId);


        return new CommonResponse<>(result);
    }


    @GetMapping()
    public CommonResponse<ReviewListResponse> getList(@ModelAttribute @Valid ReviewListRequest request , BindingResult bindingResult){
        ReviewListResponse result = reviewService.getList(request);
        log.debug("[PlanController getList] request :: {}",request);
        return new CommonResponse<>(result);
    }

    @DeleteMapping("{id}")
    public CommonResponse<String> delete(@PathVariable("id") long planId, Authentication auth){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();

        reviewService.delete(planId,user.getUser().getId());


        return new CommonResponse<>("삭제되었습니다.");
    }

    @PutMapping("/item")
    public CommonResponse<String> modify(@RequestBody ReviewModifyRequest request, Authentication auth){
        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();

        reviewService.modify(request,user.getUser().getId());

        return new CommonResponse<>("수정하였습니다.");
    }

    @PutMapping()
    public CommonResponse<String> modifyMain(@RequestBody MainReviewModifyRequest request, Authentication auth){


        CustomUserDetail user = (CustomUserDetail) auth.getPrincipal();

        reviewService.modifyMain(request, user.getUser().getId());

        return new CommonResponse<>("수정하였습니다.");
    }



}
