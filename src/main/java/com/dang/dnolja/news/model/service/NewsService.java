package com.dang.dnolja.news.model.service;

import com.dang.dnolja.global.Exception.CrawlingException;
import com.dang.dnolja.news.model.dto.BannerItemDto;
import com.dang.dnolja.news.model.dto.NewsItemDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface NewsService {
    public List<BannerItemDto> getBannerItems() throws CrawlingException;
    public List<NewsItemDto> getNewsItems();
}
