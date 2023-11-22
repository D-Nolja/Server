package com.dang.dnolja.review.model.service;

import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import com.dang.dnolja.review.controller.dto.request.ReviewPostRequest;

public interface ReviewService {


    void createReviewItem(ReviewItemRequest request, long userId);

    void createReview(ReviewPostRequest request);
}
