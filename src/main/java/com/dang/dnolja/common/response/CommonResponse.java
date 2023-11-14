package com.dang.dnolja.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

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

    private Map<String, String> errorMap;

    public CommonResponse(T info){
        setCode(ResultCode.SUCCESS.getCode());
        setMsg(ResultCode.SUCCESS.getMessage());
        setInfo(info);
    }
    public CommonResponse(ResultCode code, Exception e){
        setCode(code.getCode());
        setMsg(e.getMessage());
        //info = (T) Arrays.toString(Arrays.stream(e.getStackTrace()).toArray());
        setTimestamp(sdf.format(new Timestamp(System.currentTimeMillis())));
    }


    public CommonResponse(ResultCode code, Exception e, Map<String, String> errorMap){
        setCode(code.getCode());
        setMsg(e.getMessage());
        setErrorMap(errorMap);
        setTimestamp(sdf.format(new Timestamp(System.currentTimeMillis())));
    }





}
