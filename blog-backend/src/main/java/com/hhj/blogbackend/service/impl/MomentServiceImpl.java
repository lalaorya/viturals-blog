package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.MomentMapper;
import com.hhj.blogbackend.pojo.Moment;
import com.hhj.blogbackend.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Service
public class MomentServiceImpl extends ServiceImpl<MomentMapper, Moment> implements MomentService {

    @Autowired
    MomentMapper mapper;

    @Override
    public Integer selectCount() {
        Integer integer = mapper.selectCount(null);
        return integer;
    }

}
