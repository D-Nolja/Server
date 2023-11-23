package com.dang.dnolja.review.model.mapper;

import com.dang.dnolja.review.controller.dto.request.ReviewPostRequest;
import com.dang.dnolja.review.model.dto.PlanReview;
import com.dang.dnolja.review.controller.dto.request.ReviewItemRequest;
import com.dang.dnolja.review.model.dto.ReviewDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.List;

@Mapper
public interface ReviewMapper {
    public long  insertDailyToSpot(Map<String, Object> params);

    public void updateReviewItem(ReviewItemRequest request);

    public long getUserId(long reviewId);

    public List<ReviewDetailDto> getReviewDetailList(Map<String, Object> params);

    public PlanReview findPlanReviewById(long planId);

    public void updateReviewMetaData(ReviewPostRequest request);


}
