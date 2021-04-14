package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.ArticleTagRelationMapper;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import com.hhj.blogbackend.service.ArticleTagRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ArticleTagRelationServiceImpl extends ServiceImpl<ArticleTagRelationMapper,ArticleTagRelation> implements ArticleTagRelationService {
    @Autowired
    ArticleTagRelationMapper mapper;
    @Override
    public List<Integer> selectList(Serializable id) {
        QueryWrapper<ArticleTagRelation> wrapper = new QueryWrapper<>();
        wrapper.select("tag_id")
                .eq("article_id",id);
        List<ArticleTagRelation> articleTagRelations = mapper.selectList(wrapper);
        List<Integer> res = new ArrayList<>();
        for(ArticleTagRelation a:articleTagRelations){
            res.add(a.getTagId());
        }

//        BeanUtils.copyProperties(articleTagRelations.);
        return res;

    }
    // 不用写方法，直接调用即可
}
