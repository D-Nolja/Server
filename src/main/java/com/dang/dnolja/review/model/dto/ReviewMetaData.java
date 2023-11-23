package com.dang.dnolja.review.model.dto;

import lombok.Data;

@Data
public class ReviewMetaData {
    private long planId;
    private long userId;
    private String userName;
    private String reviewTitle;
    private String mainImg;
    private String start;
    private String end;
    private String createdAt;
    private String modifiedAt;
}
