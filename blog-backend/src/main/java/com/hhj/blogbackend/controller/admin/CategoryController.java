package com.hhj.blogbackend.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.Category;
import com.hhj.blogbackend.pojo.Tag;
import com.hhj.blogbackend.service.CategoryService;
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
@RequestMapping("/admin/category")
@Slf4j
@Api("操作分类表")
public class CategoryController {

    @Autowired
    CategoryService service;

    /**
     * 查询所有标签
     *
     * @return
     */
    @GetMapping("list")
    public Result selectList() {
        List<Category> categoryList = service.selectList();
        if (categoryList != null) {
            log.info("查询了所有分类");
            log.info(categoryList.toString());
            return Result.success(categoryList);
        } else {
            log.info("查询所有标签失败，结果为null");
            return Result.fail("标签为空~");
        }

    }

    /**
     * 分页查询所有分类
     */
    @RequiresAuthentication
    @GetMapping("listPage")
    @ApiOperation("分页查询分类表")
    public Result selectList(@RequestParam("currentPage")Integer id){
        Page<Category> page = new Page<>(id, 6);
        service.page(page);
        log.info("查询了分类表第{}页的数据",id);
        return Result.success(page);
    }


    @RequiresAuthentication
    @DeleteMapping("delete")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    @ApiOperation("删除分类根据id")
    public Result delete(Integer id){
        boolean b = service.removeById(id);
        if(b){
            log.info("删除了一个分类项");
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

    @RequiresAuthentication
    @PostMapping("update")
    @ApiOperation("更新分类表")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result update(@RequestBody Category category){
        boolean b = service.updateById(category);
        if(b){
            log.info("更新了分类表id为{}的数据",category.getId());
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }

    @RequiresAuthentication
    @PostMapping("save")
    @ApiOperation("新增标签")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result save(@RequestBody Category category){
        boolean b = service.save(category);
        if(b){
            log.info("新增了一个标签");
            return Result.success(null);
        }
        return Result.fail("操作失败，请联系管理员");
    }
}