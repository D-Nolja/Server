package com.dang.dnolja.location.model.service;


import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.mapper.SpotMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService{


    private final SpotMapper spotMapper;

    @Override
    public List<LocationDto> findAll() {
        return spotMapper.findAll();
    }
}
