package com.firenut.store.service;

import com.firenut.store.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void createTest(){
        Integer aid=10;
        Integer[]cids={3,4};
        Integer uid=13;

        String username="订单管理员";
        Order order = orderService.create(aid, cids, uid, username);
        System.out.println(order);
    }
}
