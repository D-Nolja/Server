package com.dang.dnolja.review.model.service;

import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import com.dang.dnolja.review.controller.dto.request.ReviewPostRequest;
import com.dang.dnolja.review.controller.dto.response.ReviewResponse;
import org.apache.ibatis.javassist.NotFoundException;

public interface
ReviewService {


    void createReviewItem(ReviewItemRequest request, long userId);

    void createReview(ReviewPostRequest request, long userId);


    ReviewResponse getDetail(Long planId) throws NotFoundException;
}
