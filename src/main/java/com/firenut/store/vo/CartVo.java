package com.firenut.store.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;     //加入购物车时的价格
    private Integer num;
    private String title;
    private Long realPrice; //当前的价格（实时）
    private String image;
}
