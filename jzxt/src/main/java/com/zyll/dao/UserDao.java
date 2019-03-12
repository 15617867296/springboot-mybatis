package com.zyll.dao;

import com.zyll.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);


}
