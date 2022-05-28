package com.firenut.store.mapper;

import com.firenut.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for (District d:list) {
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode(){
        String name=districtMapper.findNameByCode("610000");
        System.out.println(name);
    }
}
