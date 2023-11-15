package com.dang.dnolja.image.service.impl;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.dang.dnolja.common.util.MultipartToImgUtil;
import com.dang.dnolja.image.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Slf4j
@Service
public class ImageS3Service implements ImageService {

    private final AmazonS3 amazonS3;
    private final MultipartToImgUtil multipartToImgUtil;

    public ImageS3Service(AmazonS3 amazonS3, MultipartToImgUtil multipartToImgUtil) {
        this.amazonS3 = amazonS3;
        this.multipartToImgUtil = multipartToImgUtil;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String upload(MultipartFile multipartFile, String dirName) throws Exception {
        File file = multipartToImgUtil.convertMultipartFileToFile(multipartFile).orElseThrow(()->new RuntimeException("멀티파트를 이미지로 변환하는데 실패했습니다."));
        String fileFullPath = multipartToImgUtil.randomFileName(file, dirName);
        String url = putToS3(file, fileFullPath).orElseThrow(()->new RuntimeException("s3 업로드에 실패하였습니다."));
        multipartToImgUtil.removeFile(file);
        return url;
    }

    @Override
    public String get(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    @Override
    public void delete(String filePullPath) {

    }

    private Optional<String> putToS3(File uploadFile, String fileName){
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return Optional.of(get(bucket, fileName));
    }
}
