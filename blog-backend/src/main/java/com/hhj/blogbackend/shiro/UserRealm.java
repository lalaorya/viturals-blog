package com.hhj.blogbackend.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.hhj.blogbackend.pojo.User;
import com.hhj.blogbackend.service.UserService;
import com.hhj.blogbackend.utils.JwtUtils;
import com.hhj.blogbackend.pojo.User;
import com.hhj.blogbackend.service.UserService;
import com.hhj.blogbackend.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    // 授权
    // 把收集到的信息封装成AuthorizationInfo，然后交给SecurityManager
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        MyProfile user = (MyProfile) principalCollection.getPrimaryPrincipal();
        // 根据用户信息查询角色表，再查权限表
        // 但是我们只有用户表，所以直接查用户表的role字段
        User realUser = userService.getUserById(user.getId());
        // 田间角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission(realUser.getRole());
        return authorizationInfo;
    }

    // 认证 | 登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("认证开始");
//        String jwtToken = (String) authenticationToken.getPrincipal();
        JwtToken jwtToken = (JwtToken) authenticationToken;
        log.info("!!!!!!!!!!!!!!!");
        // 怎么得到userid的，有疑惑
        // 通过token可以获取id？似乎真可以阿
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        System.out.println(userId);
        System.out.println(userId+"-------------------------");
        User user = userService.getUserById(Integer.parseInt(userId));
        System.out.println(user.toString());

        if(user==null)  throw new UnknownAccountException("账号或密码错误");

        MyProfile myPorfile = new MyProfile();
        BeanUtil.copyProperties(user,myPorfile);

        return  new SimpleAuthenticationInfo(myPorfile,jwtToken.getCredentials(),getName());
    }
}
