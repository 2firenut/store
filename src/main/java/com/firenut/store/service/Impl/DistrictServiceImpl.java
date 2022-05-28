package com.firenut.store.service.Impl;

import com.firenut.store.entity.District;
import com.firenut.store.mapper.DistrictMapper;
import com.firenut.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
//        在进行网络数据传输的时候，为了尽量避免无效数据的传输，可以将无效数据设置为null 节省流量，另一方面提升效率
        List <District>list=districtMapper.findByParent(parent);
        for (District d:list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }

}
