package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.pojo.User;

public interface UserService extends IService<User>{

    public User getUserByUsername(String username);

    // 根据用户id查询
    public User getUserById(int id);
}
