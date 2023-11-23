package com.dang.dnolja.review.controller.dto.response;

import com.dang.dnolja.review.model.dto.ReviewMetaData;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewListResponse {

    private List<ReviewMetaData> reviews;
    private int currentPage;
    private int totalPageCount;



}
