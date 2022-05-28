package com.firenut.store.controller;

import com.firenut.store.controller.ex.*;
import com.firenut.store.entity.User;
import com.firenut.store.service.UserService;
import com.firenut.store.service.ex.InsertException;
import com.firenut.store.service.ex.UsernameDuplicatedException;
import com.firenut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

//   设置上传文件大小的最大值
    public static final int AVATAR_MAX_SIZE=10*1024*1024;

//    限制上传文件的类型
    public static final List<String>AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * 修改头像
     * @param session
     * @param file
     * @return
     */
    @RequestMapping(value = "/change_avatar",method = RequestMethod.POST)
    public JsonResult<String>changeAvatar(HttpSession session,MultipartFile file){
//        判断文件是否为空
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }

        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }

//        判断文件的类型是否为规定的类型
        String contentType=file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }

        //每次重启服务器之后 得到的parent都是新的路径，建议写成一个固定的路径
//        String parent=session.getServletContext().getRealPath("upload");
        String parent="D:/编程/springboot/store/src/main/resources/static/upload"; //  file协议为本地文件传输协议，浏览器为了安全考虑不允许直接访问。 html页面直接通过src引用本地资源，就是用file协议

        System.out.println(parent);
        File dir=new File(parent);
        if(!dir.exists()){  //如果目录不存在则创建目录
            dir.mkdir();
        }

//        获取文件的名称
        String originalFilename=file.getOriginalFilename();
        System.out.println(originalFilename);
        int index=originalFilename.lastIndexOf(".");
        String suffix=originalFilename.substring(index);

        String filename= UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest=new File(dir,filename);

//        将传入参数file中的数据写入到空文件中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }catch (FileStateException e){
            throw new FileStateException("文件状态异常");
        }

        Integer uid=getuidFromSession(session);
        String username=getusernameFromSession(session);

        String avatar=parent+"/"+filename;
        System.out.println(avatar);

        userService.changeAvatar(uid,username,avatar);

//        返回用户头像的路径给前端页面。用于前端页面的头像展示
        return new JsonResult<>(OK,avatar);
    }


    /**
     * 修改信息
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/change_info")
    public JsonResult<Void>changeInfo(User user,HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getusernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);
    }


    @RequestMapping("/get_by_uid")
    public JsonResult<User>getByUid(HttpSession session){
        User data=userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK,data);
    }



    @RequestMapping(value = "/change_password",method =RequestMethod.POST)
    public JsonResult<Void>changePassword(String oldPassword, String newPassword, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getusernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }


    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public JsonResult<Void>result(User user){
//        创建响应结果对象

        userService.reg(user);  //如果出现异常就被捕获，后面的 return new JsonResult<>(OK); 就不会执行了
        return new JsonResult<>(OK);
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonResult<User>login(String username, String password, HttpSession session){
        User data=userService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());

//        获取session域中的数据
//        System.out.println(getuidFromSession(session));
//        System.out.println(getusernameFromSession(session));

        return new JsonResult<>(OK,data);
    }




}


