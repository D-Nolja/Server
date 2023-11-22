package com.dang.dnolja.plan.model.mapper;

import com.dang.dnolja.plan.controller.dto.response.PlanItemDto;
import com.dang.dnolja.plan.model.dto.Plan;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;


@Mapper
public interface PlanMapper {

    public long createPlan(Map<String, Object> params);

    public Plan findById(long planId);

    public List<PlanItemDto> getList(Map<String, Object> params);

    public int getTotalPlanCount(Map<String, Object> params);

    public long getLastPk();

}
