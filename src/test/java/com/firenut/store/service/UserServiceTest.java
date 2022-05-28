package com.firenut.store.service;

import com.firenut.store.entity.User;
import com.firenut.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void regTest(){
        try {
            User user=new User();
            user.setUsername("Jimi");
            user.setPassword("hsf123");
            userService.reg(user);
            System.out.println("OK");
        }catch (ServiceException e) {
//            获取类的对象，然后获取类的名称
            System.out.println(e.getClass().getSimpleName());
//            获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void loginTest(){
        User user=userService.login("暴怒阿西","bnax");
        System.out.println(user);
    }


    @Test
    public void changePasswordTest(){
        userService.changePassword(13,"meimei","mei","123");
    }


    @Test
    public void getByUid(){
        System.out.println(userService.getByUid(8));
    }

    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("13553799673");
        user.setPassword("firenut@qq.com");
        user.setGender(0);
        userService.changeInfo(12,"黑客",user);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(13,"/upload/test.png","妹夫");
    }

}
