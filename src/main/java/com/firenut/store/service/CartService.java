package com.firenut.store.service;


import com.firenut.store.vo.CartVo;

import java.util.List;

public interface CartService {
    void addToCart(Integer uid,Integer pid,Integer amount,String username);

    List<CartVo>getVoByUid(Integer uid);

    Integer addNum(Integer cid,Integer uid,String username);

    Integer reduceNum(Integer cid, Integer uid, String username);

    /**
     * 查询购物车中选中的某些商品的详细信息
     * @param uid
     * @param cids
     * @return
     */
    List<CartVo>getVoByCids(Integer uid,Integer[] cids);
}
