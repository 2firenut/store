package com.firenut.store.mapper;

import com.firenut.store.entity.Cart;
import com.firenut.store.vo.CartVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartMapperTest {

    @Autowired
    CartMapper cartMapper;

    @Test
    void insert() {
        Cart cart=new Cart();
        cart.setUid(3);
        cart.setPid(2);
        cart.setNum(3);
        cart.setPrice(10L);
        Integer rows = cartMapper.insert(cart);
        System.out.println("row="+rows);
    }

    @Test
    void updateNumByCid() {
        Integer cid = 1;
        Integer num = 10;
        String modifiedUser = "购物车管理员";
        Date modifiedTime = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    void findByUidAndPid() {
        Integer uid = 3;
        Integer pid = 2;
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        System.out.println(result);
    }

    @Test
    public void findVOByUid() {
        List<CartVo> list = cartMapper.getVoByUid(3);
        System.out.println(list);
    }

    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 7, 8, 9, 10};
        List<CartVo> list = cartMapper.findVOByCids(cids);
        System.out.println("count=" + list.size());
        for (CartVo item : list) {
            System.out.println(item);
        }
    }
}