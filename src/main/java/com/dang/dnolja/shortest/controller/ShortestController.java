package com.dang.dnolja.shortest.controller;

import com.dang.dnolja.shortest.model.dto.ShortestItemDto;
import com.dang.dnolja.shortest.model.dto.ShortestRequestDto;
import com.dang.dnolja.shortest.model.dto.ShortestListDto;
import com.dang.dnolja.shortest.model.service.ShortestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shortest")
@AllArgsConstructor
public class ShortestController {
    private final ShortestService recommendShortestService;
    @GetMapping("/{type}")
    public ShortestListDto recommendShortestLocation(@ModelAttribute ShortestRequestDto request, @PathVariable("type") String type){
        //todo// 나중에 db 데이터 위도 경도 뒤집어 진거 고쳐야한다!!
        double x = request.getX();
        double y = request.getY();
        double limit = request.getLimit();
        int maxCount = request.getMaxCount();

        log.info("[ShortestController recommendShortestLocation] request :: type={} request={}", type,request);
        List<ShortestItemDto> result = recommendShortestService.recommendLocationFrom(x,y,limit, maxCount, type);
        if(result.size() ==0){
            throw new IllegalArgumentException("요청하신 위치를 기준으로 최단거리 내 관광지 데이터가 존재하지 않습니다. url을 재확인해주세요");
        }



        return ShortestListDto.builder().result(result).startX(x).startY(y).build();
    }

}
