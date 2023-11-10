package com.dang.dnolja.location.model.mapper;

import com.dang.dnolja.location.model.dto.LocationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FclMapper {

    List<LocationDto> findAll();
}
