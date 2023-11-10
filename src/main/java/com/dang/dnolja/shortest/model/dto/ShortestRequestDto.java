package com.dang.dnolja.shortest.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ShortestRequestDto {

    private double x;
    private double y;
    private double limit;
    private int maxCount;

}
