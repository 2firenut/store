package com.firenut.store.mapper;

import com.firenut.store.entity.Product;

import java.util.List;

//处理商品数据的持久层接口
public interface ProductMapper {
    /**
     * 查询热销商品的前4名
     * @return
     */
    List<Product>findHotList();

    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    Product findById(Integer id);
}
