package com.firenut.store.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class JsonResult<E> implements Serializable {

//    状态码
    private Integer state;

//    描述信息
    private String message;

//    数据 (返回给客户端的数据)
    private E data;

    public JsonResult(Throwable e){ //异常信息
        this.message=e.getMessage();
    }

    public JsonResult(Integer state){
        this.state=state;
    }

    public JsonResult(Integer state,E data){    //成功状态码及信息
        this.state=state;
        this.data=data;
    }
}
