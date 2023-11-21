package com.dang.dnolja.plan.model.mapper;

import com.dang.dnolja.plan.model.dto.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PlanMapper {

    public Plan createPlan(Map<String, Object> params);


}
