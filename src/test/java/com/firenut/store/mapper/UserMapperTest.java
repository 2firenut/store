package com.firenut.store.mapper;

import com.firenut.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insertTest(){
        User user=new User();
        user.setUsername("jay");
        user.setPassword("123");
        Integer row=userMapper.insert(user);
        System.out.println(row);
    }


    @Transactional
    @Test
    public void selectTest(){
        User user = userMapper.findByUsername("jay");
        System.out.println(user);
    }


    @Test
    void updatePasswordByUidTest(){
        userMapper.updatePasswordByUid(3,"123321","管理员",new Date());
    }

    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(3));
    }

    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(8);
        user.setPhone("13553796014");
        user.setEmail("Feng@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid(8,"/upload/smile.jpg","峰哥",new Date());
    }
}

