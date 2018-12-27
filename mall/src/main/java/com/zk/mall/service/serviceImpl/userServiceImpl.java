package com.zk.mall.service.serviceImpl;


import com.zk.mall.entity.user;
import com.zk.mall.mapper.userMapper;
import com.zk.mall.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    userMapper userMapper;


    @Override
    public boolean update(user user) {
        return userMapper.update(user);
    }

    @Override
    public boolean insert(user user) {
        return userMapper.insert(user);
    }

    @Override
    public List<user> getAllUser() {
        return userMapper.getAlluser();
    }

    @Override
    public user getuserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public boolean login(String email, String password) {
        user user = userMapper.getUserByEmail(email);
        if(user.getPassword().equals(password))
            return true;
        else
            return false;
    }
}
