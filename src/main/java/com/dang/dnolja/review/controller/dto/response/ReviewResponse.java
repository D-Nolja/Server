package com.dang.dnolja.review.controller.dto.response;

import com.dang.dnolja.review.model.dto.ReviewList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewResponse {

    private long planId;
    private long userId;
    private String userName;
    private String title;
    private String mainContent;
    private String mainImg;
    private String start;
    private String end;
    private String createdAt;
    private String modifiedAt;

    private List<ReviewList> reviewList;



}
