package com.dang.dnolja.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    private String code;
    //에러 발생 시 로그
    private String msg;
    //Dto
    private T info;
    private String timestamp;

    public CommonResponse(T info){
        setCode(ResultCode.SUCCESS.getCode());
        setMsg(ResultCode.SUCCESS.getMessage());
        setInfo(info);
    }
    public CommonResponse(ResultCode code, Exception e){
        setCode(code.getCode());
        setMsg(e.getMessage());

        setTimestamp(sdf.format(new Timestamp(System.currentTimeMillis())));
    }


    public CommonResponse(ResultCode code, Exception e, Map errorMap){
        setCode(code.getCode());
        setMsg(e.getMessage()+" "+errorMap.toString());

        setTimestamp(sdf.format(new Timestamp(System.currentTimeMillis())));
    }





}
