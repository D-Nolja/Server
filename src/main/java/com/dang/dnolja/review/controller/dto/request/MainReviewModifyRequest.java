package com.dang.dnolja.review.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MainReviewModifyRequest {
    @NotEmpty
    private Long planId;
    private String title;
    private String img;
    private String contents;
}
