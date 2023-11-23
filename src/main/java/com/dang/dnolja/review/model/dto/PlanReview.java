package com.dang.dnolja.review.model.dto;

import lombok.Data;

@Data
public class PlanReview {

    private String userName;
    private long planId;
    private long userId;
    private String title;
    private String start;
    private String end;
    private String createdAt;
    private String modifiedAt;
    private String mainTitle;
    private String mainImg;
    private String mainContents;

}
