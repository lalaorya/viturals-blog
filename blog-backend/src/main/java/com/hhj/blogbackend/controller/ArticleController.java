package com.hhj.blogbackend.controller;

import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@Slf4j
@Api("操作文章表")
public class ArticleController {

    @Autowired
    ArticleService service;

    // 通过认证才能访问该资源
    @RequiresAuthentication
    @GetMapping("list")
    @ApiOperation("查询所有文章")
    public Result selectList(){
        List<Article> articles = service.selectList();
        log.info(articles.toString());
        return Result.success(articles);
    }
}
