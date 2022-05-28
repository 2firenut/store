package com.firenut.store.service.Impl;

import com.firenut.store.entity.Address;
import com.firenut.store.entity.Order;
import com.firenut.store.entity.OrderItem;
import com.firenut.store.mapper.OrderMapper;
import com.firenut.store.service.AddressService;
import com.firenut.store.service.CartService;
import com.firenut.store.service.OrderService;
import com.firenut.store.service.ex.InsertException;
import com.firenut.store.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    CartService cartService;

    @Autowired
    AddressService addressService;

    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        List<CartVo> carts = cartService.getVoByCids(uid, cids);

        long totalPrice=0;

        for (CartVo cart : carts) {
            totalPrice+=cart.getRealPrice()*cart.getNum();
        }

//        创建订单数据对象
        Order order = new Order();
        order.setUid(uid);

//        获取地址信息
        Address address=addressService.getByAid(aid,uid);

//        补全订单数据中的收货信息
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

//        补全数据 totalPrice
        order.setTotalPrice(totalPrice);

//        设置状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
        order.setStatus(0);

        Date date = new Date();
        // 设置下单时间
        order.setOrderTime(date);

//        补全日志
        order.setCreatedUser(username);
        order.setCreatedTime(date);
        order.setModifiedTime(date);
        order.setModifiedUser(username);

//        插入订单数据
        Integer row=orderMapper.insertOrder(order);
        if(row!=1){
            throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
        }


//      创建订单详细的数据
        OrderItem orderItem=new OrderItem();
        for (CartVo cartVo :carts) {
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVo.getPid());
            orderItem.setTitle(cartVo.getTitle());
            orderItem.setImage(cartVo.getImage());
            orderItem.setPrice(cartVo.getRealPrice());
            orderItem.setNum(cartVo.getNum());

//            创建日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(date);
            orderItem.setModifiedTime(date);
            orderItem.setModifiedUser(username);

            orderMapper.insertOrderItem(orderItem);
        }
        return order;   //返回订单数据对象给控制层（为了展示在前端页面）
    }
}
