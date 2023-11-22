package com.dang.dnolja.review.model.service;

import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;

public interface ReviewService {


    void createReviewItem(ReviewItemRequest request, long userId);
}
