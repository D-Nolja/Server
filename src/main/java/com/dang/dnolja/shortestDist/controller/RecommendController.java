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
        double x = request.getX();
        double y = request.getY();
        double limit = request.getLimit();
        int maxCount = request.getMaxCount();

        log.info("[RecommendController recommendSpot] request :: {}", request);
        List<RecommendDto> result = recommendShortestService.recommendSpotFrom(x,y,limit, maxCount);
        if(result.size() ==0){
            throw new IllegalArgumentException("요청하신 위치를 기준으로 최단거리 내 관광지 데이터가 존재하지 않습니다. url을 재확인해주세요");
        }

        return new CommonResponse(result);
    }

}
