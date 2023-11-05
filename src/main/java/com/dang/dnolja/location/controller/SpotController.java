package com.dang.dnolja.location.controller;

import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.service.SpotService;
import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import static com.dang.dnolja.response.ResultCode.SUCCESS;

@RestController
@RequestMapping("/spot")
@AllArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/test")
    public CommonResponse<?> test(){
        List<LocationDto> results = spotService.findAll();

        return new CommonResponse(results);
    }


}
