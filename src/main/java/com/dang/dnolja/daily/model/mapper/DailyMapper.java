package com.dang.dnolja.daily.model.mapper;

import com.dang.dnolja.daily.model.dto.DailyDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DailyMapper {
    public void insertDaily(Map<String, Object> params);

    public long getLastPk();

    public List<DailyDetailDto> getDailyDetailList(Map<String, Object> params);

    public int getMaxDayNum(long planId);
}
