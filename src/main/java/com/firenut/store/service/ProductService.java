package com.firenut.store.service;


import com.firenut.store.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product>findHotList();

    /**
     * 根据商品的id查询商品详情
     * @param id
     * @return
     */
    Product findById(Integer id);
}
