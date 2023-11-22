package com.dang.dnolja.review.controller.dto.request;

import com.dang.dnolja.review.controller.dto.ReviewDetails;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewPostRequest {

    @NotNull
    private String reviewTitle;

    private String mainImg;
    private String mainContents;

    private List<ReviewDetails> reviewDetails;

}
