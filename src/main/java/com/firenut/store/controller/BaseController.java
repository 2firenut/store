package com.firenut.store.controller;

import com.firenut.store.controller.ex.*;
import com.firenut.store.service.ex.*;
import com.firenut.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int OK=200;

    //方法的返回值就是要传递给前端的数据
    @ExceptionHandler({ServiceException.class,FileUploadException.class})   //用于统一处理service层抛出的异常
    public JsonResult<Void>handleException(Throwable e){
        JsonResult<Void>result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已经被占用");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(4001);
            result.setMessage("输入的密码错误的异常");
        }else if(e instanceof AddressCountLimitException){
            result.setState(4002);
            result.setMessage("用户的收货地址超出上限的异常");
        }else if(e instanceof AddressNotFoundException){
            result.setState(4003);
            result.setMessage("用户的收货地址数据不存在的异常");
        }else if (e instanceof ProductNotFoundException) {
            result.setMessage("查询的商品信息不存在的异常");
            result.setState(4004);
        }else if (e instanceof CartNotFoundException) {
            result.setMessage("尝试访问的购物车数据不存在");
            result.setState(4005);
        }else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("插入时产生未知的异常");
        }else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("用户数据不存在异常");
        }else if(e instanceof UpdateException){
            result.setState(5002);
            result.setMessage("更新数据时产生未知的异常");
        }else if(e instanceof AccessDeniedException){
            result.setState(5003);
            result.setMessage("用户非法非法访问数据的异常");
        }else if(e instanceof AccessDeniedException){
            result.setState(5004);
            result.setMessage("删除数据时产生未知的异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }
        return result;
    }

    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }


    protected final String getusernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

}
