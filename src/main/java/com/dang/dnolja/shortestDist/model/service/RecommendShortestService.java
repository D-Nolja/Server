package com.dang.dnolja.shortestDist.model.service;


import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.service.SpotService;
import com.dang.dnolja.shortestDist.model.dto.RecommendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendShortestService {

    private final SpotService spotService;


    public List<RecommendDto> recommendSpotFrom(double x, double y, double limit, int maxCount){

        List<LocationDto> searchLocationResult = spotService.findAll();

        log.debug("[RecommendShortestService recommendSpotFrom] {}",searchLocationResult);


        List<RecommendDto> recommendList = new ArrayList<>();

        for(LocationDto location : searchLocationResult){
            double dist = calculateDistance(location.getY(), location.getX(), y,x );
            if(dist>=limit){
                continue;
            }
            RecommendDto recommend = RecommendDto.builder()
                                        .distance(dist)
                                        .startLatitude(x)
                                        .startLongitude(y)
                                        .targetLocation(location).build();

            recommendList.add(recommend);
        }


        Collections.sort(recommendList, Comparator.comparing(RecommendDto::getDistance));


        if(recommendList.size()<=maxCount){
            return recommendList;
        }else{
            List<RecommendDto> topNResult = new ArrayList<>();
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
