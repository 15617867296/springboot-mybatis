package com.zyll.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserEntity  {
  private  String  id;//主键
  private  String  name;//人员
  private  String  password;
}
