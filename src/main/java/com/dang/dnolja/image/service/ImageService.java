package com.dang.dnolja.image.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface ImageService {


    public String upload(MultipartFile multipartFile, String dirName) throws Exception;

    public String get(String bucket, String fileName);

    public void delete(String filePullPath);


}
