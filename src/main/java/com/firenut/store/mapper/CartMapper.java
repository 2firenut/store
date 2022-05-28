package com.firenut.store.mapper;

import com.firenut.store.entity.Cart;
import com.firenut.store.vo.CartVo;

import java.util.Date;
import java.util.List;


public interface CartMapper {
    Integer insert(Cart cart);

    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    Cart findByUidAndPid(Integer uid,Integer pid);

    List<CartVo>getVoByUid(Integer uid);

    Cart findByCid(Integer cid);

    /**
     * 查询购物车中选中的商品信息
     * @param cids
     * @return
     */
    List<CartVo> findVOByCids(Integer[] cids);
}
