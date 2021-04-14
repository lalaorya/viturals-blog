package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.ArticleCategoryRelationMapper;
import com.hhj.blogbackend.mapper.ArticleTagRelationMapper;
import com.hhj.blogbackend.pojo.ArticleCategoryRelation;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import com.hhj.blogbackend.service.ArticleCategoryRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleCategoryRelationServiceImpl extends ServiceImpl<ArticleCategoryRelationMapper,ArticleCategoryRelation> implements ArticleCategoryRelationService {
    @Autowired
    ArticleCategoryRelationMapper mapper;

    /**
     * 根据文章id查询分类id list
     * @param id
     * @return
     */
    @Override
    public List<Integer> selectList(Serializable id) {
        QueryWrapper<ArticleCategoryRelation> wrapper = new QueryWrapper<>();
        wrapper.select("category_id")
                .eq("article_id",id);
        List<ArticleCategoryRelation> articleTagRelations = mapper.selectList(wrapper);
        List<Integer> res = new ArrayList<>();
        for(ArticleCategoryRelation a:articleTagRelations){
            res.add(a.getCategoryId());
        }

//        BeanUtils.copyProperties(articleTagRelations.);
        return res;

    }
}
