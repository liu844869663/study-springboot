package com.fullmoon.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String phone;
    private String password;
    private String addr;
}
