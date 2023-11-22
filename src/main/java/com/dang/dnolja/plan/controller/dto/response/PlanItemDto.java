package com.dang.dnolja.plan.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PlanItemDto {

    private long id;
    private String title;
    private String start;
    private String end;

}
