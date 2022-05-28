package com.firenut.store.service.Impl;

import com.firenut.store.entity.Address;
import com.firenut.store.mapper.AddressMapper;
import com.firenut.store.service.AddressService;
import com.firenut.store.service.DistrictService;
import com.firenut.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    //添加用户收货地址的业务层依赖于DistrictService的业务
    @Autowired
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    /**
     *
     * @param uid       登录用户的id
     * @param username  登录的用户名
     * @param address   填写的地址信息
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count=addressMapper.countByUId(uid);
        if(count>=maxCount){
            throw new AddressCountLimitException("用户收货地址超过上限");
        }

//        address对象数据进行补全：省市区
        String provinceName=districtService.getNameByCode(address.getProvinceCode());
        String cityName=districtService.getNameByCode(address.getCityCode());
        String areaName=districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

//        uid,isDelete
        address.setUid(uid);
        Integer isDefault=count==0?1:0; //如果用户当前收获地址数量为0，就应该设置为默认地址
        address.setIsDefault(isDefault);

//        补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer rows=addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户收获地址时产生未知的异常");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        Address address1 = new Address();
        for (Address address :list) {
            address1.setAid(address.getAid());
            address1.setAid(address.getAid());

            address1.setTag(address.getTag());
            address1.setName(address.getName());
            address1.setAddress(address.getAddress());
            address1.setPhone(address.getPhone());
            address1.setAreaName(address.getAreaName());
            address1.setCityName(address.getCityName());
            address.setProvinceName(address1.getProvinceName());
            address=address1;
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result=addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收获地址不存在");
        }

//
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

//        先将所有收获地址设置为非默认
        Integer rows=addressMapper.updateNoneDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据时产生未知的异常");
        }

//        将用户选中某条地址设置为默认收获地址
        rows=addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }

        
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {

        Address result=addressMapper.findByAid(aid);

        if(result==null){
            throw new AddressNotFoundException("收货地址数据不存在");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows=addressMapper.deleteByAid(aid);

        if(rows!=1){
            throw new DeleteException("删除数据产生未知的异常");
        }

        Integer count=addressMapper.countByUId(uid);
        if (count==0){  // 只有一条记录 直接删除即可
            return;
        }

        if(result.getIsDefault()==0){// 删除的收货地址不是默认地址,直接终止即可
            return;
        }

        //如果删除的是默认地址，需要重新设置默认地址
        Address address = addressMapper.findLastModified(uid);
        rows=addressMapper.updateDefaultByAid(address.getAid(),username,new Date());

        if(rows!=1){
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address=addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问收货地址信息");
        }

        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        return address;
    }


}
