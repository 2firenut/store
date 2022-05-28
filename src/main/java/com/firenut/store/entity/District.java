package com.firenut.store.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class District { //省市区的数据实体类
    private Integer id;
    private String parent;
    private String code;
    private String name;
}