package com.dang.dnolja.location.controller;

import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.dto.request.LocationListRequest;
import com.dang.dnolja.location.model.dto.response.LocationListDto;
import com.dang.dnolja.location.model.service.LocationService;
import com.dang.dnolja.global.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @ModelAttribute @Valid LocationListRequest request,
            BindingResult bindingResult
            ) throws IllegalArgumentException {


        log.debug("[LocationController findLocations] params :: {}", request);
        LocationListDto result=  locationService.findLocation(request);

        return new CommonResponse<>(result);
    }




}
