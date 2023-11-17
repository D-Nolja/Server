package com.dang.dnolja.location.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    private long id;
    private String name;
    private String type;
    private double x;
    private double y;
    private String address;
    private String tel;
    private String openTime;
    private String parking;
    private String info;
    private String img;
}
