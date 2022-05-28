package com.firenut.store.mapper;

import com.firenut.store.entity.District;

import java.util.List;

public interface DistrictMapper {

    /**
     * 根据父代码好查询区域信息
     * @param parent
     * @return 整个父区域下的所有区域列表
     */
    List<District>findByParent(String parent);

    String findNameByCode(String code);
}
