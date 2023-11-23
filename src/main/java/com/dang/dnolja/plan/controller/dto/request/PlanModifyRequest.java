package com.dang.dnolja.plan.controller.dto.request;

import lombok.Data;

@Data
public class PlanModifyRequest {

    private long planId;
    private String title;
    private int[][] plans;
    private String start;
    private String end;
}