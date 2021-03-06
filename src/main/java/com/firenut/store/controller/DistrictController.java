package com.firenut.store.controller;

import com.firenut.store.entity.District;
import com.firenut.store.service.DistrictService;
import com.firenut.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/districts")
@RestController
public class DistrictController extends BaseController{
    @Autowired
    private DistrictService districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>>getByParent(String parent){
        List<District>data=districtService.getByParent(parent);
        return new JsonResult<>(OK,data);
    }
}
