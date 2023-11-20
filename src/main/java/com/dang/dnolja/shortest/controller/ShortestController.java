package com.dang.dnolja.shortest.controller;

import com.dang.dnolja.shortest.model.dto.ShortestItemDto;
import com.dang.dnolja.shortest.model.dto.ShortestReqDto;
import com.dang.dnolja.shortest.model.dto.ShortestListDto;
import com.dang.dnolja.shortest.model.service.ShortestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shortest")
@AllArgsConstructor
public class ShortestController {

    private final ShortestService recommendShortestService;

    @GetMapping()
    public ShortestListDto recommendShortestLocation(@ModelAttribute @Valid ShortestReqDto request, BindingResult bindingResult) throws NotFoundException {

        log.debug("[ShortestController recommendShortestLocation] request :: request={}", request);

        List<ShortestItemDto> result = recommendShortestService.recommendLocationFrom(request);

        if(result.isEmpty()){
            throw new IllegalArgumentException("요청하신 위치를 기준으로 최단거리 내 관광지 데이터가 존재하지 않습니다. url을 재확인해주세요");
        }

        return ShortestListDto.builder()
                .result(result)
                .startX(request.getX())
                .startY(request.getY())
                .build();
    }

}
