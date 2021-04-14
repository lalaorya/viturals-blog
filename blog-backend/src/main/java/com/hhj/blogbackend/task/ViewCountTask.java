package com.hhj.blogbackend.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hhj.blogbackend.mapper.ArticleMapper;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.pojo.VisitCount;
import com.hhj.blogbackend.service.ArticleService;
import com.hhj.blogbackend.service.VisitCountService;
import com.hhj.blogbackend.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 * redis中每篇博客的浏览量同步到mysql
 */
@Slf4j
@Component
public class ViewCountTask {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ArticleService service;

    @Autowired
    VisitCountService service2;


//    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(fixedDelay = 10000)
    // 每天凌晨一点触发
//    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "0 0/1 * * * ?   ")
    public void saveViewCntToDataBase(){
        log.info("进行了文章浏览量定时任务");
        List<Article> articles = service.selectList();
        for (Article article : articles) {
            String key= "article:viewCount:"+article.getId();
            Integer hGetViewCnt = (Integer) redisUtil.get(key);
            if(hGetViewCnt!=null) {
                service.update(new UpdateWrapper<Article>().eq("id",article.getId()).set("read_num",hGetViewCnt));
                // 清空缓存
                redisUtil.del(key);
            }
        }
    }

    /**
     *  把redis中的UV PV持久化到数据库
     *  并清空缓存
     */
    // 每一分钟执行一次
    @Scheduled(cron = "0 0/2 * * * ?   ")
//    @Scheduled(cron = "0 0 1 * * ?")
    public void uvpvTask(){
        log.info("进行了总浏览量和访问量定时任务");
        log.info("--------------");
        if(redisUtil.get("blog:PV")!=null&&redisUtil.sGet("blog:ip:route")!=null){
            Integer i = Integer.parseInt((String)redisUtil.get("blog:PV"));
            int l = (int)redisUtil.sGetSetSize("blog:ip:route");
            service2.update(new UpdateWrapper<VisitCount>().eq("id",1).set("pv",i+l));
        }
        if(redisUtil.get("blog:UV")!=null&&redisUtil.sGet("blog:ip")!=null){
            Integer i = Integer.parseInt((String)redisUtil.get("blog:UV"));
            int l = (int)redisUtil.sGetSetSize("blog:ip");
            service2.update(new UpdateWrapper<VisitCount>().eq("id",1).set("uv",i+l));
        }

        // 清空缓存
        redisUtil.del("blog:UV");
        redisUtil.del("blog:PV");
        redisUtil.del("blog:ip");
        redisUtil.del("blog:ip:route");
//        redisUtil.delAllIP();
    }



}
