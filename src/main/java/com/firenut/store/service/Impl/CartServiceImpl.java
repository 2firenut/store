package com.firenut.store.service.Impl;

import com.firenut.store.entity.Cart;
import com.firenut.store.entity.Product;
import com.firenut.store.mapper.CartMapper;
import com.firenut.store.mapper.ProductMapper;
import com.firenut.store.service.CartService;
import com.firenut.store.service.ex.AccessDeniedException;
import com.firenut.store.service.ex.CartNotFoundException;
import com.firenut.store.service.ex.InsertException;
import com.firenut.store.service.ex.UpdateException;
import com.firenut.store.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;



    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if(result==null){
            Cart cart=new Cart();

            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);

            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            cart.setCreatedTime(new Date());
            cart.setCreatedUser(username);

            Product product = productMapper.findById(pid);

            cart.setPrice(product.getPrice());

            Integer rows=cartMapper.insert(cart);
            if(rows!=1){
                throw new InsertException("插入商品数据时出现未知错误");
            }
        }else{
            Integer cid=result.getCid();
            Integer num=result.getNum()+amount;
            Integer rows=cartMapper.updateNumByCid(cid,num,username,new Date());
            if(rows!=1){
                throw new UpdateException("修改商品数量时产生未知的错误");
            }
        }
    }

    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        return cartMapper.getVoByUid(uid);
    }


    /**
     * 购物车指定商品数量+1的业务逻辑
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    @Override
    public Integer addNum(Integer cid,Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }

        Integer num=result.getNum()+1;

        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows!=1){
            throw new UpdateException("修改商品数量时出现未知错误,请联系系统管理员");
        }

        return num;
    }


    @Override
    public Integer reduceNum(Integer cid,Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }

        Integer num=result.getNum()-1;

        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());

        if(rows!=1){
            throw new UpdateException("修改商品数量时出现未知错误,请联系系统管理员");
        }

        return num;
    }

    @Override
    public List<CartVo> getVoByCids(Integer uid, Integer[] cids) {
        List<CartVo> list = cartMapper.findVOByCids(cids);
        Iterator<CartVo> iterator = list.iterator();
        while (iterator.hasNext()){     //处理非法访问
            CartVo cartVo = iterator.next();
            if(!cartVo.getUid().equals(uid)){
                iterator.remove();
            }
        }
        return list;
    }


}
