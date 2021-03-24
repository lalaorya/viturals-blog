package com.hhj.blogbackend.service.impl;

import com.hhj.blogbackend.mapper.MomentMapper;
import com.hhj.blogbackend.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MomentServiceImpl implements MomentService {

    @Autowired
    MomentMapper mapper;

    @Override
    public Integer selectCount() {
        Integer integer = mapper.selectCount(null);
        return integer;
    }
}
