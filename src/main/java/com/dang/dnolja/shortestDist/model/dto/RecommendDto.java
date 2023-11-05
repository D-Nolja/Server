package com.dang.dnolja.shortestDist.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor

public class RecommendDto<T> {

    private double startLatitude;
    private double startLongitude;

    //현재 위치로부터의 거리
    private double distance;

    private T targetLocation;



}
