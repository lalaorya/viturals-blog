package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleTagRelationMapper extends BaseMapper<ArticleTagRelation> {

    @Override
    List<ArticleTagRelation> selectList(@Param("ew") Wrapper<ArticleTagRelation> queryWrapper);


    @Override
    int insert(ArticleTagRelation entity);
}
