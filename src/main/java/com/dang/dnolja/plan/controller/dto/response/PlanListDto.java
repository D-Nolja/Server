package com.dang.dnolja.plan.controller.dto.response;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanListDto {

    private List<PlanItemDto> planList;
    private int currentPage;
    private int totalPageCount;


}
