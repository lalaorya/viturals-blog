package com.hhj.blogbackend.controller.client;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.dto.ArticleInfo;
import com.hhj.blogbackend.dto.PageDto;
import com.hhj.blogbackend.dto.PageDto;
import com.hhj.blogbackend.dto.TagQuery;
import com.hhj.blogbackend.dto.TimeLineInfo;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import com.hhj.blogbackend.service.ArticleService;
import com.hhj.blogbackend.service.ArticleTagRelationService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/blog")
@Api("操作文章表")
public class ArticleControllerClient {

    @Autowired
    ArticleService service;

    @Autowired
    ArticleTagRelationService atrService;
    /**
     * 分页查询博客
     */
    @PostMapping("/page")
    @ApiOperation("分页查询博客")
    public Result selectPage(@RequestBody PageDto blogPage){
        // 根据当前页数和每页条数分页查询
        Page<Article> page = new Page<>(blogPage.getCurrentPage(),blogPage.getPageSize());

        service.page(page);

        ArrayList<Article> records = (ArrayList<Article>) page.getRecords();
        ArrayList<ArticleInfo> articleInfos = new ArrayList<>();

        for(Article a:records){
            ArticleInfo articleInfo = new ArticleInfo();
            int index=a.getId();
            BeanUtils.copyProperties(a,articleInfo);
            // 根据id获取文章标签
            List<Map<String, Object>> maps = service.tagList(index);
            // 根据id获取分类标签
            articleInfo.setTags(maps);
            articleInfos.add(articleInfo);

            //// 从redis中读取阅读量
            service.getReadNumFromRedis(articleInfo);
        }



        System.out.println(articleInfos.toString());

        Page<ArticleInfo> articleInfoPage = new Page<>(blogPage.getCurrentPage(),blogPage.getPageSize());
        BeanUtil.copyProperties(page,articleInfoPage,"records");
        articleInfoPage.setRecords(articleInfos);

        return Result.success(articleInfoPage);
    }


    /**
     * 根据id查询博客
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询博客info")
    public Result selectBlog(@PathVariable("id") Integer id){
        Article ar = service.getById(id);
        if(ar!=null){
            // 新增文章阅读量
            service.addReadNum(ar);
        }

        // 根据id获取标签map
        List<Map<String, Object>> tags = service.tagList(id);
        List<Map<String, Object>> categorys = service.categoryList(id);
        ArticleInfo articleInfo = new ArticleInfo();
        BeanUtils.copyProperties(ar,articleInfo);
//        articleInfo.setContentFormat(ar.getContent());
        articleInfo.setTags(tags);
        articleInfo.setCategorys(categorys);

        System.out.println(ar.toString());

        if(articleInfo!=null){
            log.info("前台查询了id为{}的博客info",id);
            return Result.success(articleInfo);
        }
        return Result.fail("操作失败");

    }

    /**
     * 降序返回文章信息，用于归档时间线
     */
    @ApiOperation("降序返回文章信息")
    @PostMapping("/timeLine")
    public Result selectTimeLine(@RequestBody PageDto blogPage){

        // 根据当前页数和每页条数分页查询
        Page<Article> page = new Page<>(blogPage.getCurrentPage(),blogPage.getPageSize());
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<Article>().select("id", "publish_time", "title").orderByDesc("publish_time");
        service.page(page,articleQueryWrapper);

        List<Article> records = page.getRecords();
        ArrayList<TimeLineInfo> timeLineInfos = new ArrayList<>();
        for(Article a:records){
            TimeLineInfo timeLineInfo = new TimeLineInfo();
            timeLineInfo.setId(a.getId());
            timeLineInfo.setPublishTime(a.getPublishTime());
            timeLineInfo.setTitle(a.getTitle());
            timeLineInfos.add(timeLineInfo);
        }

        Page<TimeLineInfo> timeLineInfoPage = new Page<>(blogPage.getCurrentPage(),blogPage.getPageSize());
        BeanUtils.copyProperties(page,timeLineInfoPage);
        timeLineInfoPage.setRecords(timeLineInfos);

//        service.page
        log.info(timeLineInfoPage.toString());

        return Result.success(timeLineInfoPage);
    }

    /**
     * 根据标签降序返回文章信息
     */
    @ApiOperation("根据标签降序返回文章信息")
    @PostMapping("/tagTimeLine")
    public Result selectTagTimeLine(@RequestBody TagQuery tagQuery){

        // 根据标签号查询文章号
        List<ArticleTagRelation> list = atrService.list(new QueryWrapper<ArticleTagRelation>().select("article_id").eq("tag_id", tagQuery.getId()));
        List<Integer> articleList = new ArrayList<>();
        for(ArticleTagRelation a:list){
            articleList.add(a.getArticleId());
        }

        // 根据当前页数和每页条数分页查询
        Page<Article> page = new Page<>(tagQuery.getCurrentPage(),tagQuery.getPageSize());
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<Article>().select("id", "publish_time", "title")
                .in("id",articleList).orderByDesc("publish_time");
        service.page(page,articleQueryWrapper);

        List<Article> records = page.getRecords();
        ArrayList<TimeLineInfo> timeLineInfos = new ArrayList<>();
        for(Article a:records){
            TimeLineInfo timeLineInfo = new TimeLineInfo();
            timeLineInfo.setId(a.getId());
            timeLineInfo.setPublishTime(a.getPublishTime());
            timeLineInfo.setTitle(a.getTitle());
            timeLineInfos.add(timeLineInfo);
        }
        Page<TimeLineInfo> timeLineInfoPage = new Page<>(tagQuery.getCurrentPage(),tagQuery.getPageSize());
        BeanUtils.copyProperties(page,timeLineInfoPage);
        timeLineInfoPage.setRecords(timeLineInfos);

//        service.page
        log.info(timeLineInfoPage.toString());
//
//        return Result.success(timeLineInfoPage);
        return Result.success(timeLineInfoPage);
    }





}
