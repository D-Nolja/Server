package com.dang.dnolja.review.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class ReviewList {

    private int day;
    private List<ReviewDetailDto> reviewDetails;
}
