package com.dang.dnolja.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {


    //성공시
    private String code;
    //에러 발생 시 로그
    private String msg;

    //Dto
    private T info;

    public CommonResponse(ResultCode code){
        setCode(code.getCode());
        setMsg(code.getMessage());
    }

    public CommonResponse(T info){
        setCode(ResultCode.SUCCESS.getCode());
        setMsg(ResultCode.SUCCESS.getMessage());
        setInfo(info);
    }
    public CommonResponse(ResultCode code, T info){
        setCode(code.getCode());
        setMsg(code.getMessage());
        setInfo(info);
    }





}
