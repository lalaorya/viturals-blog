package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.ArticleMapper;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("ArticleServiceImpl")
public class ArticleServiceImpl  implements ArticleService {
    @Autowired
    ArticleMapper mapper;
    @Override
    public List<Article> selectList() {
        List<Article> articles = mapper.selectList(null);
        return articles;
    }

    @Override
    public Integer selectCount() {
        Integer integer = mapper.selectCount(null);
        return integer;
    }

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param queryWrapper 查询条件
     * @return
     */
    @Override
    public Page selectPage(int currentPage,Wrapper<Article> queryWrapper) {
        Page<Article> page = new Page<>(currentPage, 6);
        mapper.selectPage(page, null);
        System.out.println(page);
        return page;
    }

    @Override
    public int deleteById(Serializable id) {
        int i = mapper.deleteById(id);
        return i;
    }

    @Override
    public int updateById(Article entity) {
        int i = mapper.updateById(entity);
        return i;
    }

    @Override
    public Article selectById(Serializable id) {
        Article article = mapper.selectById(id);
        return article;
    }



}
