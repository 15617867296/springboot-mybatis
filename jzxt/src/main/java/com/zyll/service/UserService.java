package com.zyll.service;

import com.zyll.entity.UserEntity;

public interface UserService {
    public UserEntity getUserById(Integer userId);

    boolean addUser(UserEntity record);
}
