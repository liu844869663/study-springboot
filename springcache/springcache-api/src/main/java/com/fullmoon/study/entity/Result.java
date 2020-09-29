package com.fullmoon.study.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 3535180014173400094L;
    /**
     * 返回结果
     */
    private String result;
}
