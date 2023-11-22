package com.dang.dnolja.news.model.service.impl;

import com.dang.dnolja.global.Exception.CrawlingException;
import com.dang.dnolja.global.util.CrawlingUtil;
import com.dang.dnolja.news.model.dto.BannerItemDto;
import com.dang.dnolja.news.model.dto.NewsItemDto;
import com.dang.dnolja.news.model.service.NewsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NewsServiceImpl  implements NewsService {
    private final CrawlingUtil crawlingUtil;

    @Override
    public List<BannerItemDto> getBannerItems() throws CrawlingException {

        String url = "https://knto.or.kr/";
        String uri = "/index";



        Elements divs =  crawlingUtil.getDoc(url + uri).select("div.swiper-slide");
        List<BannerItemDto> banners = new ArrayList<>();

        for (Element div : divs) {
            Elements imgElements = div.select("div a img[src^=/media]");

            for (Element imgElement : imgElements) {
                if (banners.size() == 1) {
                    break;
                }

                String title = imgElement.attr("alt").trim();
                String img = url + imgElement.attr("src");
                String link = imgElement.parent().attr("href");

                int carriageReturnIndex = title.indexOf('\r');
                String bannerTitle = (carriageReturnIndex != -1) ? title.substring(0, carriageReturnIndex) : title;

                banners.add(BannerItemDto.builder()
                        .title(bannerTitle)
                        .img(img)
                        .link(link)
                        .build());
            }
        }
        return banners;
    }

    @Override
    public List<NewsItemDto> getNewsItems() throws CrawlingException {
        String url = "https://www.newsjeju.net";
        String uri = "/news/articleList.html?sc_section_code=S1N18&view_type=sm";


        Elements divs = crawlingUtil.getDoc(url + uri).select("div.list-block");

        List<NewsItemDto> items = new ArrayList<>();
        int itemsToRetrieve = 3;

        for (Element div : divs) {
            if (items.size() == itemsToRetrieve) {
                break;
            }

            Elements listImageElements = div.select("div.list-image a span");
            Elements listDatedElements = div.select("div.list-dated");
            Elements linkElements = div.select("div.list-image a");

            if (!listImageElements.isEmpty() && !listDatedElements.isEmpty() && !linkElements.isEmpty()) {
                String title = listImageElements.text();
                String content = div.select("p.list-summary").text();
                String uploadDate = listDatedElements.text();
                String link = url + linkElements.attr("href");

                items.add(NewsItemDto.builder()
                        .title(title)
                        .content(content)
                        .uploadDate(uploadDate)
                        .link(link)
                        .build());
            }
        }

        return items;
    }
}
