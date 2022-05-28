package com.firenut.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/** 收货地址数据的实体类 */
@Data
@EqualsAndHashCode
@ToString
public class Address extends BaseEntity implements Serializable {
    private Integer aid;   //自增
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;

    // Generate: Getter and Setter、Generate hashCode() and equals()、toString()
}