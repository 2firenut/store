package com.firenut.store.service;

import com.firenut.store.service.CartService;
import com.firenut.store.vo.CartVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Test
    void addToCart() {
        cartService.addToCart(3,10000007,10,"峰哥");
    }

    @Test
    public void addNum(){
        System.out.println(cartService.addNum(1, 3, "女朋友"));
    }

    @Test
    public void getVOByCids() {
        Integer[] cids = {1, 2,3,4,5, 6, 7, 8, 9, 10};
        Integer uid = 3;
        List<CartVo> list = cartService.getVoByCids(uid, cids);
        System.out.println("count=" + list.size());
        for (CartVo item : list) {
            System.out.println(item);
        }
    }
}