package com.hhj.blogbackend.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.dto.MomentChangeDto;
import com.hhj.blogbackend.pojo.Moment;
import com.hhj.blogbackend.service.ArticleCommentService;
import com.hhj.blogbackend.service.MomentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@Api("动态表接口")
@RestController
@RequestMapping("/admin/moment")
public class MomentController {

    @Autowired
    MomentService service;

    @RequiresAuthentication
    @GetMapping("count")
    @ApiOperation("查询动态总数")
    public Result monentCount(){
        log.info("查询了动态总数");
        Integer integer = service.selectCount();
        return Result.success(integer);
    }

    @RequiresAuthentication
    @GetMapping("list")
    @ApiOperation("分页查询动态")
    public Result momentList(@RequestParam("currentPage")Integer id){
        Page<Moment> page = new Page<>(id, 6);
        service.page(page,null);
        return Result.success(page);
    }

    @RequiresAuthentication
    @PutMapping("update")
    @ApiOperation("更新动态")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result updateMomemt(@RequestBody MomentChangeDto dto){
        Moment moment = new Moment();
        BeanUtil.copyProperties(dto,moment);
        boolean b = service.updateById(moment);
        if(b){
            log.info("更新了序号为{}的动态",dto.getId());
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @RequiresAuthentication
    @PostMapping("save")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    @ApiOperation("新增动态")
    public Result saveMomemt(@RequestBody MomentChangeDto dto){
        Moment moment = new Moment();
        BeanUtil.copyProperties(dto,moment);
        moment.setStatus(1);
//        moment.setId(service.selectCount()+1);
        boolean b = service.save(moment);
        if(b){
            log.info("新增了一个动态");
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }


    @RequiresAuthentication
    @PostMapping("changeStatus")
    @ApiOperation("修改动态的状态")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    public Result changeStatus(@RequestBody Map<String,Integer> map){
        Integer id=map.get("id");
        Integer status = map.get("status");
        log.info("修改前{}",status);
        // 这里不用取反，为什么我也不知道
        // 在swagger测试的时候取反是正确的，但是使用vue测试的时候不取反是正确的
        // 前面有一处代码也有这个问题，估计和element有关??
        // 经过测试，如何不发布的时候传过来的本该是0，但是确是1.所有我们不需要在反转了
        UpdateWrapper<Moment> eq = new UpdateWrapper<Moment>().set("status", status).eq("id", id);
        boolean b = service.update(eq);
        Moment byId = service.getById(id);
        log.info("修改后{}",byId.getStatus());
        if(b){
            log.info("修改了动态{}的stauts字段",id);
            return Result.success(null);
        }
        return Result.fail("操作失败");
    }

    @RequiresAuthentication
    @GetMapping("delete")
    @RequiresPermissions(value = "root",logical = Logical.AND)
    @ApiOperation("删除动态")
    public Result deleteMomemt(@RequestParam("id") Integer id){
        boolean b = service.removeById(id);
        if(b){
            log.info("删除了序号为{}的动态",id);
            return Result.success(null);
        }
        return Result.fail("操作失败");
    }
}
