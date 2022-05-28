package com.firenut.store.controller;

import com.firenut.store.entity.Product;
import com.firenut.store.service.ProductService;
import com.firenut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){
        return new JsonResult<>(OK, productService.findHotList());
    }

    @RequestMapping("/details/{id}")
    public JsonResult<Product>getById(@PathVariable("id")Integer id){
        Product data = productService.findById(id);
        return new JsonResult<>(OK,data);
    }

}
