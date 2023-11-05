package com.dang.dnolja.shortestDist.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class RecommendRequestDto {

    private double x;
    private double y;
    private double limit;
    private int maxCount;



}
