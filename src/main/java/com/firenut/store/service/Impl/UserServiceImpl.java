package com.firenut.store.service.Impl;

import com.firenut.store.entity.User;
import com.firenut.store.mapper.UserMapper;
import com.firenut.store.service.UserService;
import com.firenut.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);

        if(result==null){
            throw new UserNotFoundException("用户数据不存在");
        }

        String salt=result.getSalt();
        String oldPassword=result.getPassword();
        String newMd5Password=getMD5Password(password,salt);
        if(!newMd5Password.equals(oldPassword)){
            throw new PasswordNotMatchException("用户密码错误");
        }

//       判断is_delete字段是否为1表示被标记为删除
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

//       返回数据时只需返回部分数据，提升系统的性能
        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar()); //头像
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result=userMapper.findByUid(uid);

        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        //判断原密码是否正确
        String oldMd5Password=getMD5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码错误");
        }

//        修改新密码
        String newMd5Password=getMD5Password(newPassword,result.getSalt());

        Integer rows=userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());

        if(rows!=1){
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        User user=new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid,String username,User user) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }

        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);

        Integer rows=userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新数据时产生异常");
        }

    }

    /**
     * 更新用户头像
     * @param uid
     * @param avatar
     * @param username
     */
    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
//        查询当前用户数据是否存在
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }


        Integer rows=userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新用户头像产生未知的异常");
        }
    }

    @Override
    public void reg(User user) {
//        调用findByUsername()判断用户是否被注册过
        User result=userMapper.findByUsername(user.getUsername());

        if(result!=null){   //用户名
            //把异常抛给上层处理
            throw new UsernameDuplicatedException("用户名被占用");
        }

//        密码加密处理的实现 md5算法的形式
//        串 + passowrd +串 --> md5算法进行加密，连续加密3次
//        盐值 + passowrd +盐值
        String oldPassword=user.getPassword();
        String salt= UUID.randomUUID().toString().toUpperCase();
        String md5Password=getMD5Password(oldPassword,salt);
        user.setPassword(md5Password);
        user.setSalt(salt); //需要记录盐值，不然密码就找不到了

//       补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows=userMapper.insert(user);
        if(rows!=1){
            //把异常抛给上层处理
            throw new InsertException("在用户注册的过程中产生了未知的异常");
        }

    }

    //    定义应该md5算法的加密
    public String getMD5Password(String password,String salt){
//        md5加密算法的调用
        for (int i = 0; i < 3; i++) {   //加密3次
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }


}
