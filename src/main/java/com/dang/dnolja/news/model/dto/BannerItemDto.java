package com.dang.dnolja.news.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BannerItemDto {
    private String img;
    private String link;

    private String title;


}
