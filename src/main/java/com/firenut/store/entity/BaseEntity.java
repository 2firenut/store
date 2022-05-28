package com.firenut.store.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

//实体类的基类
@EqualsAndHashCode
@ToString
@Data
public class BaseEntity implements Serializable {
    String createdUser; //日志创建人
    Date   createdTime;   //日志创建时间
    String modifiedUser;//最后修改执行人
    Date modifiedTime ;//最后修改时间
}
