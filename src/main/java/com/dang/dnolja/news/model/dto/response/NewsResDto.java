package com.dang.dnolja.news.model.dto.response;


import com.dang.dnolja.news.model.dto.BannerItemDto;
import com.dang.dnolja.news.model.dto.NewsItemDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsResDto {

    List<NewsItemDto> items;
    List<BannerItemDto> banners;
}
