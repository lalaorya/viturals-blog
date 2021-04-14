package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.pojo.ArticleCategoryRelation;

import java.io.Serializable;
import java.util.List;

public interface ArticleCategoryRelationService extends IService<ArticleCategoryRelation> {

    /**
     * 根据文章id查询分类id
     */
    List<Integer> selectList(Serializable id);
}
