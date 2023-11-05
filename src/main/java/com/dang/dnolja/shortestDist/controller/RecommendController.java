package com.dang.dnolja.shortestDist.controller;

import com.dang.dnolja.response.CommonResponse;
import com.dang.dnolja.response.ResultCode;
import com.dang.dnolja.shortestDist.model.dto.RecommendDto;
import com.dang.dnolja.shortestDist.model.dto.RecommendRequestDto;
import com.dang.dnolja.shortestDist.model.service.RecommendShortestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recommendation")
@AllArgsConstructor
public class RecommendController {
    private final RecommendShortestService recommendShortestService;
    @GetMapping("/spot")
    public CommonResponse<RecommendDto> recommendSpot(@ModelAttribute RecommendRequestDto request){
        //todo// 나중에 db 데이터 위도 경도 뒤집어 진거 고쳐야한다!!
        double y = request.getX();
        double x = request.getY();
        double limit = request.getLimit();
        int maxCount = request.getMaxCount();

        log.info("[RecommendController recommendSpot] request :: {}", request);
        List<RecommendDto> result = recommendShortestService.recommendSpotFrom(x,y,limit, maxCount);

        return new CommonResponse(ResultCode.SUCCESS, result);
    }

}
