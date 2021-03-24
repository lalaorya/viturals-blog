package com.hhj.blogbackend.controller.admin;

import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.service.ArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("文章评论表接口")
@RestController
@RequestMapping("/admin/comment")
public class ArticleCommentController {

    @Autowired
    ArticleCommentService service;

    @RequiresAuthentication
    @GetMapping("count")
    @ApiOperation("查询评论总数")
    public Result articleCount(){
        log.info("查询了评论总数");
        Integer integer = service.selectCount();
        return Result.success(integer);
    }

}
