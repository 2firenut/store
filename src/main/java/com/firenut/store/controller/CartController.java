package com.firenut.store.controller;

import com.firenut.store.service.CartService;
import com.firenut.store.util.JsonResult;
import com.firenut.store.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/add_to_cart")
    public JsonResult<Void>addToCart(Integer pid, Integer amount, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getusernameFromSession(session);
        cartService.addToCart(uid,pid,amount,username);
        return new JsonResult<>(OK);
    }

    @GetMapping({"/",""})
    public JsonResult<List<CartVo>>getVoByUid(HttpSession session){
        Integer uid = getuidFromSession(session);
        List<CartVo> data = cartService.getVoByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/add_num/{cid}",method = RequestMethod.POST)
    public JsonResult<Integer>addNum(@PathVariable("cid")Integer cid,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        Integer data = cartService.addNum(cid, uid, username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/reduce_num/{cid}",method = RequestMethod.POST)
    public JsonResult<Integer>reduceNum(@PathVariable("cid")Integer cid,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getusernameFromSession(session);
        Integer data = cartService.reduceNum(cid, uid, username);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JsonResult<List<CartVo>>getVoByCids(Integer[]cids,HttpSession session){
//       这里写@PathVariable会报错...,它匹配的是一个路径变量，如果有多个同名路径变量就会出错
//        http://localhost:8080/carts/list?cids=7&cids=8&cids=13&cids=14&cids=17&cids=1&cids=2&cids=3&cids=4
        Integer uid=getuidFromSession(session);
        List<CartVo>data=cartService.getVoByCids(uid,cids);
        return new JsonResult<>(OK,data);
    }

}
