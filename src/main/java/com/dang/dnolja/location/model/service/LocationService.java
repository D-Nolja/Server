package com.dang.dnolja.location.model.service;

import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.dto.request.LocationListRequest;
import com.dang.dnolja.location.model.dto.response.LocationListDto;

import java.util.List;
import java.util.Map;

public interface LocationService {

    List<LocationDto> findAll();


    LocationListDto  findLocation(LocationListRequest request);

}
