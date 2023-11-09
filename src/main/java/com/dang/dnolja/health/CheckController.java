package com.dang.dnolja.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @ResponseBody
    @GetMapping("/check")
    public String check(){
        return "ok";
    }


    @ResponseBody
    @GetMapping("/authCheck")
    public String authCheck(){
        return "권한 체크 완료";
    }
}
