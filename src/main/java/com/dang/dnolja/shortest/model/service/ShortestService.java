package com.dang.dnolja.shortest.model.service;


import com.dang.dnolja.location.model.dto.response.LocationDto;
import com.dang.dnolja.location.model.mapper.FclMapper;
import com.dang.dnolja.location.model.mapper.SpotMapper;
import com.dang.dnolja.location.model.mapper.StayMapper;
import com.dang.dnolja.shortest.model.dto.ShortestItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;



@Slf4j
@Service
@RequiredArgsConstructor
public class ShortestService {

    private final SpotMapper spotMapper;

    private final StayMapper stayMapper;
    private final FclMapper fclMapper;

    public List<ShortestItemDto> recommendLocationFrom(double x, double y, double limit, int maxCount, String type){

        if(type==null && !(type.equals("fcl")||type.equals("stay")||type.equals("spot"))) throw new IllegalArgumentException(String.format("%s는 지원하지 않습니다. (fcl, stay, spot 중에서만 지원합니다.", type));

        List<LocationDto> searchLocationResult = new ArrayList<>();

        searchLocationResult = spotMapper.findByType(type);

        log.debug("[ShortestService recommendLocationFrom] {}",searchLocationResult);


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


        Collections.sort(recommendList, Comparator.comparing(ShortestItemDto::getDistance));

        log.debug("[ShortestService recommendLocationFrom] recommendList ::{}",recommendList);
        if(recommendList.size()<=maxCount){
            return recommendList;
        }else{
            List<ShortestItemDto> topNResult = new ArrayList<>();
            for(int i=0; i<maxCount; i++){
                topNResult.add(recommendList.get(i));
            }
            return topNResult;
        }

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
