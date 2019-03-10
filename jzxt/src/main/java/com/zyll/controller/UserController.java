package com.zyll.controller;

import com.zyll.entity.UserEntity;
import com.zyll.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    @ResponseBody
    public UserEntity toIndex(HttpServletRequest request, UserEntity userEntity){
         try{
             int userId = Integer.parseInt(request.getParameter("id"));
             userEntity = this.userService.getUserById(userId);
         }catch (Exception e){
             e.printStackTrace();
         }
        return userEntity;
    }
}
