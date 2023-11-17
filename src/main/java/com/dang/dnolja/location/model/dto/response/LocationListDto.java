package com.dang.dnolja.location.model.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;
@Data
@Builder
public class LocationListDto {

    private List<LocationDto> searchResult;
    private int currentPage;
    private int totalPageCount;

}
