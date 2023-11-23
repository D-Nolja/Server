package com.dang.dnolja.review.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDetailDto {
    private long reviewId;
    private int order;
    private long spotId;
    private String name;
    private String category;
    private String type;
    private double x;
    private double y;
    private String content;
    private String img;
}