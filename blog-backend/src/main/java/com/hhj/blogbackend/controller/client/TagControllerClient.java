package com.hhj.blogbackend.controller.client;


import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api("前端操作标签表")
public class TagControllerClient {

    @Autowired
    TagService service;


    @ApiOperation("返回所有标签")
    @GetMapping("tagList")
    public Result selectTagList(){
        List<Map<String, Object>> maps = service.listMaps();
        if(maps!=null){
            log.info("前台查询了所有标签");
            return Result.success(maps);
        }
        return Result.fail("操作失败");
    }


}
