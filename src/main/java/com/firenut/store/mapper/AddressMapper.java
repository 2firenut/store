package com.firenut.store.mapper;

import com.firenut.store.entity.Address;

import java.util.Date;
import java.util.List;

//收获地址持久层的接口
public interface AddressMapper {

//    新增用户的收获地址
    Integer insert(Address address);

//    根据用户id查询收获地址的数量
    Integer countByUId(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid
     * @return
     */
    List<Address>findByUid(Integer uid);


    /**
     * 用于在设置默认地址前检查该收货地址id是否存在
     * 比如在页面还没刷新的时候该收货地址id的信息已经被别人删除
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    Integer updateNoneDefault(Integer uid);

    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据收货id删除信息
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 按照修改时间的先后，查询对应的收货信息（limit限制了只查第一条）
     * @param uid
     * @return
     */
    Address findLastModified(Integer uid);
}
