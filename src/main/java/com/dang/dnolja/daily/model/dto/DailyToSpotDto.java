package com.dang.dnolja.daily.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyToSpotDto {
    private long dailyId;
    private long spotId;
    private String content;
    private int order;
}
