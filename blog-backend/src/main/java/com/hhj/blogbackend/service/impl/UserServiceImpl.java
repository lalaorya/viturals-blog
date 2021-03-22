package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhj.blogbackend.mapper.UserMapper;
import com.hhj.blogbackend.pojo.User;
import com.hhj.blogbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User res = userMapper.selectByUsername(username);
        return res;
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.selectById(id);
        return user;
    }
}
