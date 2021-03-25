package com.hhj.blogbackend.service;

import com.hhj.blogbackend.pojo.Category;
import com.hhj.blogbackend.pojo.Tag;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有分类
     */
    List<Category> selectList();

    /**
     * 根据分类id查询分类名字，多对多
     */
    public  List<String> selectCategoryById(List<Integer> list);
}
