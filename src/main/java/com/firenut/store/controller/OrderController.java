package com.firenut.store.controller;

import com.firenut.store.entity.Order;
import com.firenut.store.service.OrderService;
import com.firenut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order>create(Integer aid, Integer[]cids, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username = getusernameFromSession(session);
        Order data=orderService.create(aid,cids,uid,username);
        return new JsonResult<>(OK,data);
    }
}
