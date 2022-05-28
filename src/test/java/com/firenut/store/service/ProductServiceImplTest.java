package com.firenut.store.service;

import com.firenut.store.entity.Product;
import com.firenut.store.service.ProductService;
import com.firenut.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    void findHotList() {
        List<Product> hotList = productService.findHotList();
        for (Product product : hotList) {
            System.out.println(product);
        }
    }

    @Test
    public void findById() {
        try {
            Integer id = 10000004;
            Product result = productService.findById(id);
            System.out.println(result);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}