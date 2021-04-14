package com.hhj.blogbackend.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.dto.PageDto;
import com.hhj.blogbackend.pojo.User;
import com.hhj.blogbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("操作用户表的接口")
@RestController
@Slf4j
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService service;

    /**
     * 分页查询返回所有用户
     */
    @RequiresAuthentication
    @PostMapping("list")
    @ApiOperation("分页查询用户表")
    public Result selectPage(@RequestBody PageDto pageDto){

        Page<User> userPage = new Page<>(pageDto.getCurrentPage(), pageDto.getPageSize());
        System.out.println(userPage.toString());
        service.page(userPage);
        log.info("查询用户表的数据");
        return Result.success(userPage);
    }


    @RequiresAuthentication
    @GetMapping("delete")
    @ApiOperation("删除某个用户")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result delete(@RequestParam("id") Integer id){
        boolean b = service.removeById(id);
        if(b){
            log.info("删除了某个用户");
            return Result.success(null);
        }

        return Result.fail("操作失败");
    }






}
