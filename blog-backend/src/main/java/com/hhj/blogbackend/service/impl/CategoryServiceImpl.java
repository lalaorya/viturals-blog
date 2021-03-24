package com.hhj.blogbackend.service.impl;

import com.hhj.blogbackend.mapper.CategoryMapper;
import com.hhj.blogbackend.pojo.Category;
import com.hhj.blogbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper mapper;
    @Override
    public List<Category> selectList() {
        List list = mapper.selectList(null);
        return  list;
    }
}
