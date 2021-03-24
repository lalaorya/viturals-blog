package com.hhj.blogbackend.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.service.ArticleService;
import com.hhj.blogbackend.service.CategoryService;
import com.hhj.blogbackend.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/article")
@Slf4j
@Api("操作文章接口")
public class ArticleController {

    @Autowired
    ArticleService service;
    @Autowired
    TagService tagService;
    @Autowired
    CategoryService categoryService;

    // 通过认证才能访问该资源
    @RequiresAuthentication
    @GetMapping("list")
    @ApiOperation("查询所有文章")
    public Result selectList(){
        log.info("查询了所有文章");
        List<Article> articles = service.selectList();
        log.info(articles.toString());
        return Result.success(articles);
    }

    @RequiresAuthentication
    @GetMapping("count")
    @ApiOperation("查询文章总数")
    public Result articleCount(){
        log.info("查询了文章总数");
        Integer integer = service.selectCount();
        return Result.success(integer);
    }

    /**
     * 分页查询
     * 传入当前页码index，返回分页记录数目
     */
    @RequiresAuthentication
    @GetMapping("blogList/{index}")
    @ApiOperation("分页查询")
    public Result selectPage(@PathVariable("index")Integer currentPage){
        System.out.println("======================");
        Page page = service.selectPage(currentPage,null);
        log.info("查询文章表了第{}页的数据",currentPage);
        return Result.success(page);
    }

    /**
     * 删除文章
     * 传入文章id index
     */
    @RequiresAuthentication
    @DeleteMapping("delete")
    @ApiOperation("删除文章")
    public Result deleteById(@RequestParam("id")Integer index){
        int i = service.deleteById(index);
        log.info("删除文章表中id为{}的文章",index);
        if(i==1)    return Result.success(200,"删除成功",null);
        return Result.fail("删除失败,数据库中查无此项");
    }

    /**
     * 文章置顶与否
     * 传入文章id index
     * 思路是先根据id查找文章，更新文章status字段，update传入entiy
     */
    @RequiresAuthentication
    @GetMapping("top")
    @ApiOperation("文章置顶与否操作")
    // 这里其实应该弄成一个dto对象用于数据传输会更好的，后续再优化把
    public Result topById(@RequestParam("blog_id")Integer id,@RequestParam("is_top")Integer isTop){
        log.info("----------------------------"+id+"++++"+isTop);
//        int temp=(isTop==0?1:0);
        Article article = service.selectById(id);
        article.setTop(isTop);
        int i = service.updateById(article);
        log.info("更新文章表中id为{}的文章置顶状态,结果为{}",id,i);
        if(i==1)    return Result.success(200,"操作成功",null);
        return Result.fail("操作失败,数据库中查无此项");
    }

    /**
     * 根据传入id查询文章
     * return：文章基本信息 所属分类标签
     */
    public Result slectById(@PathVariable(value = "id",required = false)Integer id){
        if(id==null)    return Result.success(null);
        Article article = service.selectById(id);
        tagService.


    }
}