package com.firenut.store.controller;

import com.firenut.store.entity.Address;
import com.firenut.store.service.AddressService;
import com.firenut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/address")
@RestController
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/add_new_address")
    public JsonResult<Void>addNewAddress(Address address, HttpSession session){
        Integer uid=getuidFromSession(session);
        String username=getusernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>>getUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<Address>data=addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }

    @RequestMapping(value = "/set_default/{aid}",method = RequestMethod.POST)
    public JsonResult<Void>setDefault(@PathVariable("aid")Integer aid, HttpSession session){
        addressService.setDefault(aid,getuidFromSession(session),getusernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping(value = "/delete/{aid}")
    public JsonResult<Void>delete(@PathVariable("aid")Integer aid,HttpSession session){
        addressService.delete(aid,getuidFromSession(session),getusernameFromSession(session));
        return new JsonResult<>(OK);
    }


}
