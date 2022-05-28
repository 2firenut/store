package com.firenut.store.service;

import com.firenut.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceTest {
    @Autowired
    AddressService addressService;

    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setPhone("110");
        address.setName("警察蜀黍");  //这里的name是指收货人的名字，不一定是登录的用户名
        addressService.addNewAddress(1,"峰哥",address);
    }

    @Test
    public void updateDefaultTest(){
        addressService.setDefault(8,13,"少峰");
    }


    @Test
    public void delete(){
        addressService.delete(8,13,"管理员");
    }

}
