package com.hhj.blogbackend.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.FriendsLink;
import com.hhj.blogbackend.pojo.Tag;
import com.hhj.blogbackend.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询所有标签
     */
    @RequiresAuthentication
    @GetMapping("listPage")
    @ApiOperation("分页查询标签表")
    public Result selectList(@RequestParam("currentPage")Integer id){
        Page<Tag> page = new Page<>(id, 6);
        service.page(page);
        log.info("查询了友链表第{}页的数据",id);
        return Result.success(page);
    }

    @RequiresAuthentication
    @PostMapping("update")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    @ApiOperation("更新标签标签表")
    public Result update(@RequestBody Tag tag){
        boolean b = service.updateById(tag);
        if(b){
            log.info("更新了标签表id为{}的数据",tag.getId());
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

    @RequiresAuthentication
    @PostMapping("save")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    @ApiOperation("新增标签")
    public Result save(@RequestBody Tag tag){
        boolean b = service.save(tag);
        if(b){
            log.info("新增了一个标签");
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

    @RequiresAuthentication
    @DeleteMapping("delete")
    @ApiOperation("删除标签根据id")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result delete( Integer id){
        boolean b = service.removeById(id);
        if(b){
            log.info("删除了一个标签");
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }


}
