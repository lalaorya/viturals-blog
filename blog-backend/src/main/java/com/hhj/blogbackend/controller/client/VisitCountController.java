package com.hhj.blogbackend.controller.client;

import cn.hutool.http.HttpRequest;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.VisitCount;
import com.hhj.blogbackend.service.VisitCountService;
import com.hhj.blogbackend.utils.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@Api("操作访问量表")
public class VisitCountController {

    @Autowired
    VisitCountService service;

//    @Autowired
//    IPUtils utils;

//    @GetMapping("getPVUV")
//    @ApiOperation("从redis或者数据库中获取pv uv")
//    public Result getPVUV(HttpServletRequest request){
//        // 测试
////        String realIp = utils.getRealIp(request);
////        System.out.println(realIp);
//        VisitCount pvuv = service.getPVUV();
//        if (pvuv!=null){
//            return Result.success(pvuv);
//        }else {
//            return Result.fail("pv uv无法获取");
//        }
//    }

    @GetMapping("statisUP")
    @ApiOperation("统计uv pv")
    public Result statisUP(@Param("route") String route,HttpServletRequest request){
        // 测试
//        String realIp = utils.getRealIp(request);
//        System.out.println(realIp);
        VisitCount visitCount = service.staticUP(route, request);
        return Result.success(visitCount);
    }

    @GetMapping("/admin/visitCount/count")
    @ApiOperation("统计uv pv")
    public Result selectUP(){
        // 后台管理从数据库mysql获取，不从redis
        VisitCount byId = service.getById(1);
        if (byId!=null) return Result.success(byId);
        return Result.fail("操作失败");
    }
}
