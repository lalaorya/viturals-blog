package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.hhj.blogbackend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Override
    User selectById(Serializable id);

    User selectByUsername(@Param("username") String username);

    // 根据条件获取用户
    @Override
    User selectOne(Wrapper<User> queryWrapper);


}
