package com.dang.dnolja.location.model.service;


import com.dang.dnolja.location.model.dto.response.LocationDto;
import com.dang.dnolja.location.model.dto.response.LocationListDto;
import com.dang.dnolja.location.model.mapper.SpotMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {


    private final SpotMapper spotMapper;

    @Override
    public List<LocationDto> findAll() {
        return null;
    }

    @Override
    public LocationListDto findLocation(Map<String, String> map){

        Map<String, Object> params = new HashMap<>();
        params.put("keyWord", map.get("keyWord") == null ? "" : map.get("wkeyWord"));
        int pageNo = Integer.valueOf(map.get("pageNo") == null ? "1" : map.get("pageNo"));
        int sizePerPage = Integer.valueOf(map.get("sizePerPage")== null ? "5" : map.get("pageNo"));
        int start = pageNo - sizePerPage - sizePerPage;
        params.put("start", start);

        log.debug("[LocationServiceImpl findLocation] params :: {}", params);


        List<LocationDto> result = spotMapper.findAll(params);

        int totalLocationCount = SpotMapper.getTotalLocationCount(params);
        int totalPageCount = (totalLocationCount -1)/ sizePerPage+1;

        return LocationListDto.builder()
                .searchResult(result)
                .currentPage(pageNo)
                .totalPageCount(totalPageCount)
                .build();
    }
}
