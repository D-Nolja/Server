package com.dang.dnolja.plan.controller.dto.response;

import com.dang.dnolja.daily.controller.dto.response.DailyListResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class PlanDetailDto {

    private long planId;
    private long userId;
    private String title;
    private String start;
    private String end;
    private String createdAt;
    private String modifiedAt;
    private List<DailyListResponse> planDetails;
}
