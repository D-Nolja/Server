package com.dang.dnolja.review.controller;

import com.dang.dnolja.auth.details.CustomUserDetail;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.plan.model.service.PlanService;
import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import com.dang.dnolja.review.model.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
