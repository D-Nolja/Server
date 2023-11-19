package com.dang.dnolja.global.util;


import com.dang.dnolja.global.Exception.CrawlingException;
import org.springframework.stereotype.Component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


@Component
public class CrawlingUtil {

    public Document getDoc(String url) throws CrawlingException {
    try{
        return Jsoup.connect(url).get();
    }catch(Exception e){
        throw new CrawlingException(String.format("%s 크롤링이 실패했습니다.", url));
    }


    }



}
