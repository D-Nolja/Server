package com.dang.dnolja.review.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ReviewMapper {
    public long  insertDailyToSpot(Map<String, Object> params);
}
