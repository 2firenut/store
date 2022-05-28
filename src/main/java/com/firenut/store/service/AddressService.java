package com.firenut.store.service;

import com.firenut.store.entity.Address;

import java.util.List;

//收获地址业务层接口
public interface AddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address>getByUid(Integer uid);

    /**
     * 修改某用户的某条收获数据为默认收货地址
     * @param aid
     * @param uid
     * @param username
     */
    void setDefault(Integer aid,Integer uid,String username);


    /**
     * 删除一条收货地址信息
     * @param aid
     * @param uid
     * @param username
     */
    void delete(Integer aid,Integer uid,String username);

    /**
     * 根据收货地址aid查询收货地址详细
     * @param aid
     * @param uid 传入uid是为了进行验证，避免非法访问数据
     * @return
     */
    Address getByAid(Integer aid,Integer uid);


}
