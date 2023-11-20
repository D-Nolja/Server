package com.dang.dnolja.shortest.model.service;


import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.mapper.FclMapper;
import com.dang.dnolja.location.model.mapper.SpotMapper;
import com.dang.dnolja.location.model.mapper.StayMapper;
import com.dang.dnolja.shortest.model.dto.ShortestItemDto;
import com.dang.dnolja.shortest.model.dto.ShortestReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;



@Slf4j
@Service
@RequiredArgsConstructor
public class ShortestService {

    private final SpotMapper spotMapper;




    public List<ShortestItemDto> recommendLocationFrom(ShortestReqDto request) throws NotFoundException {

        Map<String, Object> params = getSearchOption(request);

        List<LocationDto> searchLocationResult = spotMapper.findAllwithoutPagenation(params);

        if(searchLocationResult.isEmpty()) throw new NotFoundException("조건을 만족하는 검색 결과가 존재하지 않습니다.");

        log.debug("[ShortestService recommendLocationFrom] searchLocationResult :: {}",searchLocationResult);

        List<ShortestItemDto> recommendList = getShortestList(searchLocationResult, request.getX(), request.getY(), request.getLimit());

        recommendList.sort(Comparator.comparing(ShortestItemDto::getDistance));

        log.debug("[ShortestService recommendLocationFrom] recommendList ::{}",recommendList);

        if(recommendList.size()<=request.getMaxCount()){
            return recommendList;
        }else{
            return getTopNResult(recommendList, request.getMaxCount());
        }
    }

    private List<ShortestItemDto> getTopNResult(List<ShortestItemDto> recommendList, int maxCount) {
        List<ShortestItemDto> topNResult = new ArrayList<>();
        for(int i=0; i<maxCount; i++){
            topNResult.add(recommendList.get(i));
        }
        return topNResult;
    }

    private List<ShortestItemDto> getShortestList(List<LocationDto> searchLocationResult, double x, double y, double limit) {
        List<ShortestItemDto> recommendList = new ArrayList<>();
        for(LocationDto location : searchLocationResult){
            double dist = calculateDistance(location.getY(), location.getX(), y,x );
            log.debug("[ShortestService recommendLocationFrom] x: {}, y: {}, limit :{}, dist :{}", x, y, limit, dist);
            if(dist>=limit){
                continue;
            }
            ShortestItemDto recommend = ShortestItemDto.builder()
                    .distance(dist)
                    .targetLocation(location).build();

            recommendList.add(recommend);
        }

        return recommendList;

    }

    private Map<String, Object> getSearchOption(ShortestReqDto request) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", request.getCategory() == null ? "" : request.getCategory());
        params.put("keyWord", request.getKeyword() == null ? "" : request.getKeyword());

        return params;
    }


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);


        double earthRadius = 6371;

        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }



}
