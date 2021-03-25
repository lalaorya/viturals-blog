package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhj.blogbackend.dto.ArticleDetail;
import com.hhj.blogbackend.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Override
    Article selectById(Serializable id);


    @Override
    List<Article> selectList(Wrapper<Article> queryWrapper);

    @Override
    Integer selectCount(Wrapper<Article> queryWrapper);

    @Override
    <E extends IPage<Article>> E selectPage(E page, @Param("ew") Wrapper<Article> queryWrapper);

    @Override
    int deleteById(Serializable id);

    @Override
    int updateById(@Param("et") Article entity);

    /**
     * 根据文章查询其下的标签，多表查询
     */
//    @Select("select * from ")
//    ArticleDetail selectDetailById(Serializable id);

}


