package com.dang.dnolja.shortest.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor

public class ShortestItemDto<T> {


    //현재 위치로부터의 거리
    private double distance;

    private T targetLocation;



}
