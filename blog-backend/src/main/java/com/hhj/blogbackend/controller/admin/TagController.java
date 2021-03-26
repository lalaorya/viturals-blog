package com.hhj.blogbackend.controller.admin;

import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.Tag;
import com.hhj.blogbackend.service.TagService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/tag")
@Slf4j
@Api("操作标签表")
public class TagController {
    @Autowired
    TagService service;

    /**
     * 查询所有标签
     * @return
     */
    @GetMapping("list")
    public Result selectList(){
        List<Tag> tags = service.selectList();
        if(tags!=null){
            log.info("查询了所有标签");
            log.info(tags.toString());
            return Result.success(tags);
        }else{
            log.info("查询所有标签失败，结果为null");
            return Result.fail("标签为空~");
        }
    }
}
