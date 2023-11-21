package com.dang.dnolja.daily.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DailyMapper {
    public long insertDaily(Map<String, Object> params);

    public long insertDailyToSpot(Map<String, Object> params);
}
