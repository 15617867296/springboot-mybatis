package com.zyll.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
public class UserEntity implements Serializable {
  private static final long serialVersionUID = -3946734305303957850L;
  private  String  id;//主键
  private  String  name;//人员
  private  String  password;
}
