package com.firenut.store.service.Impl;

import com.firenut.store.entity.Product;
import com.firenut.store.mapper.ProductMapper;
import com.firenut.store.service.ProductService;
import com.firenut.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        for (Product product : hotList) {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return hotList;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if(product==null){
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }

        product.getPrice();

        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        // 返回查询结果
        return product;
    }
}
