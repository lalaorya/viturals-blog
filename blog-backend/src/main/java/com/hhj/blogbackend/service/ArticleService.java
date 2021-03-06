package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.dto.ArticleDetail;
import com.hhj.blogbackend.dto.ArticleInfo;
import com.hhj.blogbackend.pojo.Article;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ArticleService extends IService<Article>{
    // 获取所有博文
    List<Article> selectList();

    // 查询博客总数
    Integer selectCount();

    //分页查询
    Page selectPage(int currentPage,Wrapper<Article> queryWrapper);

    // 根据id删除
    int deleteById(Serializable id);

    // 更新文章

    int updateById1(Article entity);

    Article selectById(Serializable id);

    // 根据id查询文章细节
    ArticleDetail selectDetailById(Serializable id);

    // 保存文章
    boolean saveBlog(ArticleDetail articleDetail);


    public List<Map<String,Object>> tagList(Serializable id);

    public List<Map<String,Object>> categoryList(Serializable id);

    boolean addReadNum(Article article);

    void getReadNumFromRedis(ArticleInfo articleInfo);
}
