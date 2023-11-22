package com.dang.dnolja.review.model.mapper;

import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ReviewMapper {
    public long  insertDailyToSpot(Map<String, Object> params);

    public void updateReviewItem(ReviewItemRequest request);

    public long getUserId(long reviewId);
}
