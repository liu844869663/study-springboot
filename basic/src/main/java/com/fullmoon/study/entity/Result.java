package com.fullmoon.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Result<T> {
    private static final int SUCCESS = 1;

    private static final int FAIL = 2;
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public static Result success(){
        return new Result().code(SUCCESS);
    }

    public static Result fail(){
        return new Result().code(FAIL);
    }

    public static Result fail(Throwable e){
        return new Result().code(FAIL).message(e);
    }

    public Result<T> data(T data){
        this.setData(data);
        return this;
    }

    public Result<T> message(Throwable e){
        this.setMessage(e.getMessage());
        return this;
    }

    public Result<T> code(int code){
        this.setCode(code);
        return this;
    }

}
