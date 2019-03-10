package com.zyll.service.impl;

import com.zyll.dao.UserDao;
import com.zyll.entity.UserEntity;
import com.zyll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserEntity getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(UserEntity record) {
        Boolean result=false;
        try{
            userDao.insertSelective(record);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
