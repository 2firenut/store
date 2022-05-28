package com.firenut.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/** 订单数据的实体类 （不显示订单所包含的的具体商品项信息）*/
@Data
@ToString
@EqualsAndHashCode
public class Order extends BaseEntity implements Serializable {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;

    // Generate: Getter and Setter、Generate hashCode() and equals()、toString()
}