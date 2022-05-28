package com.firenut.store.service;

import com.firenut.store.entity.Order;

/**
 * 处理订单和订单数据的业务层接口
 */
public interface OrderService {

    /**
     * 创建订单及订单项
     * @param aid  收货地址id
     * @param cids 购物车中选中商品在购物车中的cid
     * @param uid
     * @param username
     * @return
     */
    Order create(Integer aid,Integer[]cids,Integer uid,String username);
}
