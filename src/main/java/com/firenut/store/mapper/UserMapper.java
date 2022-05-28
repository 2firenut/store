package com.firenut.store.mapper;

import com.firenut.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {

    Integer updateAvatarByUid(Integer uid,String avatar,String modifiedUser,Date modifiedTime);

    /**
     * 插入用户数据 (注册用户)
     * 插入前要判断数据库中是否有用户名，有则不允许插入
     * @param user
     * @return
     */
    Integer insert(User user);


    /**
     * 根据用户名来查询用户的数据（查询用户数据）
     */
    User findByUsername(String username);


    /**
     * 根据id修改用户的密码
     * @param uid
     * @param password
     * @param modifiedUser  修改的用户名
     * @param modifiedTime  修改时间
     * @return
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据id查询用户信息
     * @param uid
     * @return
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     */
    Integer updateInfoByUid(User user);
}
