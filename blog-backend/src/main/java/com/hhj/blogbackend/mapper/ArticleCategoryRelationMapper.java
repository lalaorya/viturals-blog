package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhj.blogbackend.pojo.ArticleCategoryRelation;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleCategoryRelationMapper extends BaseMapper<ArticleCategoryRelation> {
    @Override
    List<ArticleCategoryRelation> selectList(@Param("ew") Wrapper<ArticleCategoryRelation> queryWrapper);
}
