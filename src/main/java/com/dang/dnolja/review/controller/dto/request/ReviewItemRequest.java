package com.dang.dnolja.review.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data

public class ReviewItemRequest {

    @NotEmpty
    private Long reviewId;
    @NotEmpty
    private String img;
    @NotEmpty
    private String contents;
}
