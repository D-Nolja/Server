package com.dang.dnolja.location.model.mapper;


import com.dang.dnolja.location.model.dto.LocationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SpotMapper {

    List<LocationDto> findById(Long id);

    List<LocationDto> findByName(String name);


    List<LocationDto> findByType(String type);

    List<LocationDto> findLocationList(Map<String, Object> req);

    List<LocationDto> findAll();


    int getTotalLocationCount(Map<String, Object> req);
}
