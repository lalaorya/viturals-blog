package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.pojo.ArticleTagRelation;

import java.io.Serializable;
import java.util.List;

public interface ArticleTagRelationService  extends IService<ArticleTagRelation> {
    /**
     * 根据文章id查询标签id
     */
    List<Integer> selectList(Serializable id);

    /**
     * 批量插入
     */




}
