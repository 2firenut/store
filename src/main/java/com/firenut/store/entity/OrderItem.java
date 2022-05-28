package com.firenut.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/** 订单中的商品数据(用于罗列订单中的具体商品信息) */
@Data
@ToString
@EqualsAndHashCode
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;

    // Generate: Getter and Setter、Generate hashCode() and equals()、toString()
}
