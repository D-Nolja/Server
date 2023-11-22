package com.dang.dnolja.plan.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PlanItemDto {


    private String userName;
    private long planId;
    private String title;
    private String start;
    private String end;

}
