package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hhj.blogbackend.pojo.Article;

import java.util.List;

public interface ArticleService {
    // 获取所有博文
    List<Article> selectList(Wrapper<Article> queryWrapper);
}
