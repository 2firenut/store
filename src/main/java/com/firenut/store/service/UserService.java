package com.firenut.store.service;

import com.firenut.store.entity.User;

public interface UserService {
    void reg(User user);

    User login(String username,String password);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    User getByUid(Integer uid);

    public void changeInfo(Integer uid,String username,User user);

    void changeAvatar(Integer uid,String avatar,String username);
}
