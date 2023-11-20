package com.dang.dnolja.location.model.service;


import com.dang.dnolja.location.model.dto.LocationDto;
import com.dang.dnolja.location.model.dto.request.LocationListRequest;
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
    public LocationListDto findLocation(LocationListRequest request){

        Map<String, Object> params = getSearchOption(request);

        log.debug("[LocationServiceImpl findLocation] params :: {}", params);

        List<LocationDto> result = spotMapper.findAllwithPagenation(params);

        int totalLocationCount = spotMapper.getTotalLocationCount(params);
        int totalPageCount = (totalLocationCount -1)/ (int) params.get("sizePerPage")+1;

        return LocationListDto.builder()
                .searchResult(result)
                .currentPage((int) params.get("currentPage"))
                .totalPageCount(totalPageCount)
                .build();
    }

    private Map<String, Object> getSearchOption(LocationListRequest request) {
        Map<String, Object> params = new HashMap<>();
        log.debug("[LocationServiceImpl findLocation] request :: {}", request);

        params.put("category", request.getCategory() == null ? "" : request.getCategory());
        params.put("keyWord", request.getKeyword() == null ? "" : request.getKeyword());
        int pageNo = request.getCurrentPage() == null ? 1: request.getCurrentPage();
        int sizePerPage = request.getSizePerPage()== null ? 5 : request.getSizePerPage();
        int start = pageNo * sizePerPage - sizePerPage;
        params.put("start", start);
        params.put("sizePerPage", sizePerPage);
        params.put("currentPage", pageNo);

        return params;
    }
}
