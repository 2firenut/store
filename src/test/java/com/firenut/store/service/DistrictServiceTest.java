package com.firenut.store.service;

import com.firenut.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictServiceTest {
    @Autowired
    private DistrictService districtService;

    @Test
    public void getByParent(){
        List<District>list=districtService.getByParent("86");
        for (District d:list){
            System.out.println(d);
        }
    }
}
