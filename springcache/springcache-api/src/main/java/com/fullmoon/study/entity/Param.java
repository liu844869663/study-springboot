package com.fullmoon.study.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Param implements Serializable {
    private static final long serialVersionUID = 5310491093475495710L;
    /**
     * 消息
     */
    private String message;
    /**
     * 开始点
     */
    private Integer start;
    /**
     * 结束点
     */
    private Integer end;
}
