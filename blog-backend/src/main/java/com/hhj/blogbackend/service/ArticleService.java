package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.pojo.Article;

import java.io.Serializable;
import java.util.List;

public interface ArticleService{
    // 获取所有博文
    List<Article> selectList();

    // 查询博客总数
    Integer selectCount();

    //分页查询
    Page selectPage(int currentPage,Wrapper<Article> queryWrapper);

    // 根据id删除
    int deleteById(Serializable id);

    int updateById(Article entity);

    Article selectById(Serializable id);
}
