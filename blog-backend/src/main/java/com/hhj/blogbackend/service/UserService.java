package com.hhj.blogbackend.service;

import com.hhj.blogbackend.pojo.User;

public interface UserService {

    public User getUserByUsername(String username);

    // 根据用户id查询
    public User getUserById(int id);
}
