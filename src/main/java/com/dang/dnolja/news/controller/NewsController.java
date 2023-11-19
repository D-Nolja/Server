package com.dang.dnolja.news.controller;


import com.dang.dnolja.global.Exception.CrawlingException;
import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.global.util.CrawlingUtil;
import com.dang.dnolja.news.model.dto.BannerItemDto;
import com.dang.dnolja.news.model.dto.NewsItemDto;
import com.dang.dnolja.news.model.dto.response.NewsResDto;
import com.dang.dnolja.news.model.service.NewsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/news")
@AllArgsConstructor
public class NewsController {


    private final NewsService newsService;

    @GetMapping()
    public CommonResponse<NewsResDto> getNews() throws CrawlingException {

        List<BannerItemDto> banners = newsService.getBannerItems();
        List<NewsItemDto> newsItems = newsService.getNewsItems();

        return new CommonResponse<>(NewsResDto.builder().banners(banners).items(newsItems).build());
    }


}
