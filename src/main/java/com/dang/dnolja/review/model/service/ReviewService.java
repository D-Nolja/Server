package com.dang.dnolja.review.model.service;

import com.dang.dnolja.review.controller.dto.request.*;
import com.dang.dnolja.review.controller.dto.response.ReviewListResponse;
import com.dang.dnolja.review.controller.dto.response.ReviewResponse;
import org.apache.ibatis.javassist.NotFoundException;

public interface
ReviewService {


    public void createReviewItem(ReviewItemRequest request, long userId);

    public void createReview(ReviewPostRequest request, long userId);


    public ReviewResponse getDetail(Long planId) throws NotFoundException;

    public ReviewListResponse getList(ReviewListRequest request);

    void delete(long planId, long id);

    void modify(ReviewModifyRequest request, long id);

    void modifyMain(MainReviewModifyRequest request, long id);
}
