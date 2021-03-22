package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hhj.blogbackend.mapper.ArticleMapper;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper mapper;
    @Override
    public List<Article> selectList() {
        List<Article> articles = mapper.selectList(null);
        return articles;
    }
}
