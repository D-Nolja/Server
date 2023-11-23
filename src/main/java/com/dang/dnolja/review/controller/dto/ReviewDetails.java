package com.dang.dnolja.review.controller.dto;

import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import lombok.Data;

import java.util.List;

@Data
public class ReviewDetails {
    private int day;
    private List<ReviewItemRequest> dailyReview;
}
