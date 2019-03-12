package com.zyll.service.impl;


import com.zyll.config.RedisKeyPrefix;
import com.zyll.dao.UserDao;
import com.zyll.entity.UserEntity;
import com.zyll.service.UserService;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
@Log4j2
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<String,UserEntity> redisTemplate;



    @Override
    public UserEntity getUserById(Integer userId) {
        //拼接缓存中的key
        String key = RedisKeyPrefix.USER + userId;
        //从缓存中判断是否存在值
        Boolean hashKey=redisTemplate.hasKey(key);
        if(hashKey){
            //从缓存中取值
            UserEntity userEntity=redisTemplate.opsForValue().get(key);
            return userEntity;
        }
        //查库
        UserEntity userEntity=userDao.selectByPrimaryKey(userId);
        //将库的值放入redis中
        redisTemplate.opsForValue().set(key, userEntity, 600, TimeUnit.SECONDS);
        return userEntity;
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
    /**
     * 更新用户
     * 如果缓存存在，删除
     * 如果缓存不存在，不操作
     *
     * @param user 用户
     */
    public void updateUser(UserEntity user) {
        log.info("更新用户start...");
        userDao.updateByPrimaryKeySelective(user);
        String userId = user.getId();
        // 缓存存在，删除缓存
        String key = "user_" + userId;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("更新用户时候，从缓存中删除用户 >> " + userId);
        }
    }

    /**
     * 删除用户
     * 如果缓存中存在，删除
     */
    public void deleteById(int id) {
        log.info("删除用户start...");
        userDao.deleteByPrimaryKey(id);

        // 缓存存在，删除缓存
        String key = "user_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("删除用户时候，从缓存中删除用户 >> " + id);
        }
    }
}
