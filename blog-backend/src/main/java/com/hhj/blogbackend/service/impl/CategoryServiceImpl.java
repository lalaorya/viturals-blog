package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.CategoryMapper;
import com.hhj.blogbackend.pojo.Category;
import com.hhj.blogbackend.pojo.Tag;
import com.hhj.blogbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Autowired
    CategoryMapper mapper;
    @Override
    public List<Category> selectList() {
        List list = mapper.selectList(null);
        return  list;
    }

    @Override
    public List<String> selectCategoryById(List<Integer> list) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.select("name")
                .in("id",list);
        List<Category> categoryList = mapper.selectList(wrapper);
        List<String> res = new ArrayList<>();
        for(Category c:categoryList){
            res.add(c.getName());
        }
        return res;
    }
}
