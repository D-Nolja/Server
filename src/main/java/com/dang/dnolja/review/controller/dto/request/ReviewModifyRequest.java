package com.dang.dnolja.review.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewModifyRequest {
    @NotEmpty
    private Long reviewId;
    private String contents;
    private String img;
}
