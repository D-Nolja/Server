package com.dang.dnolja.daily.model.dto;

import lombok.Data;

@Data
public class DailyDetailDto {
    private long reviewId;

    private int order;
    private int spotId;
    private String name;
    private String category;
    private String type;
    private double x;
    private double y;
    private String openTime;
    private String img;
}
