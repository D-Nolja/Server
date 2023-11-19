package com.dang.dnolja.image.controller;

import com.dang.dnolja.global.response.CommonResponse;
import com.dang.dnolja.image.controller.dto.response.UploadResultResponse;
import com.dang.dnolja.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;


    @PostMapping("/{type}")
    public ResponseEntity<CommonResponse<UploadResultResponse>> uploadProfile(@RequestPart("image") MultipartFile image, @PathVariable("type") String dirType) throws Exception {

        log.error("[ImageController uploadProfile] :: 이미지를 업로드합니다.");
        String url = null;
        try{
           url = imageService.upload(image, dirType);
        }catch(Exception e){
            e.printStackTrace();
        }



        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse(new UploadResultResponse(url)));
    }


}
