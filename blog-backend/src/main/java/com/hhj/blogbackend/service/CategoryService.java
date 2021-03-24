package com.hhj.blogbackend.service;

import com.hhj.blogbackend.pojo.Category;
import com.hhj.blogbackend.pojo.Tag;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有标签
     */
    List<Category> selectList();
}
