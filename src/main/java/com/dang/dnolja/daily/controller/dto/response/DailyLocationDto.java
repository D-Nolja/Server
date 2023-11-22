package com.dang.dnolja.daily.controller.dto.response;

import lombok.Data;

@Data
public class DailyLocationDto {

    private long locationId;
    private int order;
    private String name;
    private String category;
    private String type;
    private double x;
    private double y;
    private String openTime;
    private String img;

}
