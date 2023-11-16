package com.dang.dnolja.location.model.mapper;


import com.dang.dnolja.location.model.dto.LocationDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SpotMapper {

    List<LocationDto> findById(Long id);

    List<LocationDto> findByName(String name);


    List<LocationDto> findByType(String type);

    List<LocationDto> findAll();

}
