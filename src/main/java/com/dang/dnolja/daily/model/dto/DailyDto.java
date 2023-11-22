package com.dang.dnolja.daily.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyDto {
    private long dailyId;
    private long planId;
    private int dayNum;
    private String createdAt;
    private String modifiedAt;
}
