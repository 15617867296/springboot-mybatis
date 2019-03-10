package com.zyll.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CustomerEntity {
    private  String  id; //客户id
    private  String  name; //客户名称
    private  String  operdate;//创建时间
    private  String  context;//内容
    private  String  ntegral;//积分
}
