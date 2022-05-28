package com.firenut.store.mapper;

import com.firenut.store.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(1);
        address.setName("峰哥");
        address.setPhone("123456");
        addressMapper.insert(address);
    }


    @Test
    public void countByUid(){
        Integer rows=addressMapper.countByUId(1);
        System.out.println(rows);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(13);
        for (Address address : list) {
            System.out.println(address);
        }
    }


    @Test
    public void findByAid(){
        Address address = addressMapper.findByAid(2);
        System.out.println(address);
    }


    @Test
    public void updateNoneDefault(){
        addressMapper.updateNoneDefault(1);
    }

    @Test
    public void updateDefaultByAid(){
        addressMapper.updateDefaultByAid(9,"明明",new Date());
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(9);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(13));
    }
}
