package com.dang.dnolja.daily.controller.dto.response;

import com.dang.dnolja.daily.model.dto.DailyDetailDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DailyListResponse {

    private int day;
    private List<DailyDetailDto> dailyPlan;

}
