package com.hhj.blogbackend.controller.admin;

import com.hhj.blogbackend.common.Result;
import com.hhj.blogbackend.pojo.User;
import com.hhj.blogbackend.service.UserService;
import com.hhj.blogbackend.utils.JwtUtils;
import com.hhj.blogbackend.dto.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("/admin")
@Slf4j
@Api("用户登录登出接口")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    @ApiOperation("根据用户名和密码登录")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user = userService.getUserByUsername(loginDto.getUsername());
//        System.out.println(user.toString());
        if(user==null)  {
            log.error("==================用户名不存在====================");
            return Result.fail(400,"用户名或密码错误",null);
        }
//        Assert.notNull(user,"用户不存在");// 不需要进行断言
        // 否则进行加密
        // 加密内容 加密盐值 加密次数
        String newPassword = new Md5Hash(loginDto.getPassword(), loginDto.getUsername(), 1024).toHex();
        log.info("==================加密后的密码{}==================",newPassword);
        if(!user.getPassword().equals(newPassword)){
            log.error("=================用户名或密码错误====================");
            return Result.fail("用户名或密码错误");
        }
        // 给前端返回jwt
        String jwt = jwtUtils.generateToken(user.getId());
        System.out.println(jwt);
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("nick",user.getNick());
        map.put("avatar",user.getAvatar());
        map.put("introduction",user.getIntroduction());
        map.put("role",user.getRole());
        return Result.success(map);
    }

    @ApiOperation("注销用户")
    // 需要认证才能访问该资源
//    @RequiresAuthentication
    @GetMapping("logout")

    public Result logout(){
        System.out.println(11111);
//        Subject subject = SecurityUtils.getSubject();
//        log.info(subject.toString()+"------------===================-------------");
        SecurityUtils.getSubject().logout();
        log.info("=================================用户注销===========================");
        return Result.success(200,"注销成功",null);
    }
}
