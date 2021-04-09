package com.hhj.blogbackend.service.impl;

import cn.hutool.core.net.Ipv4Util;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.UserMapper;
import com.hhj.blogbackend.pojo.User;
import com.hhj.blogbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.util.IPAddressUtil;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

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
