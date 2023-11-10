package com.dang.dnolja.shortest.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ShortestListDto {
    private double startX;
    private double startY;
    private List<ShortestItemDto> result;


}
