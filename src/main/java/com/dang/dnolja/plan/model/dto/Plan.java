package com.dang.dnolja.plan.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Plan {
    private long planId;
    private long userId;
    private String title;
    private String start;
    private String end;
    private String createdAt;
    private String modifiedAt;
}
