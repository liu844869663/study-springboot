package com.fullmoon.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public class People implements Serializable {
    private static final long serialVersionUID = 2065705527554614956L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 地址
     */
    private String addr;
    /**
     * 孩子
     */
    private List<Child> children;
}
