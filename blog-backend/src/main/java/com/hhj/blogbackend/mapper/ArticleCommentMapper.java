package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhj.blogbackend.pojo.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {
    /**
     * 查询评论总数
     * @param queryWrapper
     * @return
     */
    @Override
    Integer selectCount(Wrapper<ArticleComment> queryWrapper);


}
