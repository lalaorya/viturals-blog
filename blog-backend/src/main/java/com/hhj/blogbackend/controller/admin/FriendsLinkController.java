package com.hhj.blogbackend.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.dto.FriendStatusInfo;
import com.hhj.blogbackend.pojo.FriendsLink;
import com.hhj.blogbackend.service.FriendsLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("操作友链表")
@RestController
@Slf4j
@RequestMapping("/admin/friends")
public class FriendsLinkController {

    @Autowired
    FriendsLinkService service;

    @RequiresAuthentication
    @GetMapping("list")
    @ApiOperation("分页查询友链表")
    public Result selectList(@RequestParam("currentPage")Integer id){
        Page<FriendsLink> page = new Page<>(id, 6);
        service.page(page);
        log.info("查询了友链表第{}页的数据",id);
        return Result.success(page);
    }

    @RequiresAuthentication
    @GetMapping("delete")
    @ApiOperation("根据id删除友链")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result deleteById(Integer id){
        System.out.println(id+"=============");
        boolean b = service.removeById(id);
        if(b){
            log.info("删除了友链表id为{}的数据",id);
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");

    }

    @RequiresAuthentication
    @PostMapping("update")
    @ApiOperation("根据id更新友链")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result updateById(@RequestBody FriendsLink friendsLink){
        boolean b = service.updateById(friendsLink);
        if(b){
            log.info("更新了友链表id为{}的数据",friendsLink.getId());
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

    @RequiresAuthentication
    @PostMapping("save")
    @ApiOperation("添加友链")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result save(@RequestBody FriendsLink friendsLink){
        boolean b = service.save(friendsLink);
        if(b){
            log.info("添加了一条友链");
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

    @RequiresAuthentication
    @PostMapping("status")
    @ApiOperation("更新了友链状态")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result updateStatus(@RequestBody FriendStatusInfo info){
        boolean b = service.update(new UpdateWrapper<FriendsLink>().set("is_check", info.getStatus()).eq("id", info.getId()));
        if(b){
            log.info("更新了友链状态");
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

}
