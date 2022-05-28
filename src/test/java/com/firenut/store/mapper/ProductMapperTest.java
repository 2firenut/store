package com.firenut.store.mapper;

import com.firenut.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotListTest(){
        List<Product> hotList = productMapper.findHotList();
        for (Product product : hotList) {
            System.out.println(product);
        }
    }

    @Test
    public void findById(){
        System.out.println(productMapper.findById(10000001));
    }

}
