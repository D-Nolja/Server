package com.dang.dnolja.review.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private long review_id;
    private long dailyId;
    private long spotId;
    private String content;
    private int order;
    private String img;
}