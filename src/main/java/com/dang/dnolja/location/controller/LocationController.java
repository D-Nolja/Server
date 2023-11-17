package com.dang.dnolja.location.controller;

import com.dang.dnolja.common.response.ResultCode;
import com.dang.dnolja.location.model.dto.request.LocationListRequest;
import com.dang.dnolja.location.model.dto.response.LocationDto;
import com.dang.dnolja.location.model.dto.response.LocationListDto;
import com.dang.dnolja.location.model.service.LocationService;
import com.dang.dnolja.common.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Slf4j
@RestController
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {

    static final String err = "요청하신 내용에 %s가 존재하지 않습니다.";
    private final LocationService locationService;

    @GetMapping("/test")
    public CommonResponse<?> test(){
        List<LocationDto> results = locationService.findAll();

        return new CommonResponse(results);
    }

    @GetMapping()
    public CommonResponse<LocationListDto> findLocations(
            @RequestParam("keyWord") Map<String, String> params
            ) throws IllegalArgumentException {


        log.debug("[LocationController findLocations] params :: {}", params);
        LocationListDto result=  locationService.findLocation(params);

        return new CommonResponse<>(result);
    }




}
