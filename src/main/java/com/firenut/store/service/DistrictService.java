package com.firenut.store.service;

import com.firenut.store.entity.District;

import java.util.List;

public interface DistrictService {
    List<District>getByParent(String parent);

    String getNameByCode(String code);
}
