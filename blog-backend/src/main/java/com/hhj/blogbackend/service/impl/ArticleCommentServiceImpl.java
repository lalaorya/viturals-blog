package com.hhj.blogbackend.service.impl;

import com.hhj.blogbackend.mapper.ArticleCommentMapper;
import com.hhj.blogbackend.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    ArticleCommentMapper mapper;

    @Override
    public Integer selectCount() {
        Integer integer = mapper.selectCount(null);
        return integer;
    }
}
