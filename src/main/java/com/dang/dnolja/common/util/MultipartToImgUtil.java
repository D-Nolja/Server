package com.dang.dnolja.common.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class MultipartToImgUtil {

    public Optional<File> convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return Optional.of(convFile);
    }

    public void removeFile(File file){
        if(file.delete()){
            return;
        }else{
            throw new RuntimeException(String.format("%s을 지우지 못했습니다.", file.getName()));
        }
    }

    public String randomFileName(File file, String dirName){
        log.error("[MultipartToImgUtil randomFileName] fileName : {}",dirName);
        log.error("[MultipartToImgUtil randomFileName] randomFileName : {}", dirName+"/"+UUID.randomUUID()+file.getName());
        return dirName+"/"+UUID.randomUUID()+file.getName();
    }




}
