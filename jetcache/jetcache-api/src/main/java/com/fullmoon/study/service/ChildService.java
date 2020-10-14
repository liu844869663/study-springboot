package com.fullmoon.study.service;

import com.fullmoon.study.entity.Child;
import com.fullmoon.study.entity.People;

import java.util.List;

public interface ChildService {
    public List<Child> getChildren(People people);

    public Child getChildByName(String name);
}
