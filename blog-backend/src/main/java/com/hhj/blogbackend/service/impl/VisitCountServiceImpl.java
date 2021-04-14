package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.mapper.VisitCountMapper;
import com.hhj.blogbackend.pojo.VisitCount;
import com.hhj.blogbackend.service.VisitCountService;
import com.hhj.blogbackend.utils.IPUtils;
import com.hhj.blogbackend.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class VisitCountServiceImpl extends ServiceImpl<VisitCountMapper,VisitCount> implements VisitCountService {

    @Autowired
    VisitCountMapper mapper;

    @Autowired
    RedisUtil util;

    @Autowired
    IPUtils ipUtils;



//    @Override
//    public VisitCount getPVUV() {
//        // 如果redis中有数据的化
//        String key="blog:PV";
//        util
//        Integer pv = (Integer)util.get(key);
//        Integer uv = (Integer)util.get("post:UV");
//        if (pv!=null&&uv!=null){
//            return new VisitCount(1,uv,pv);
//        }
//        VisitCount visitCount = mapper.selectById(1);
//
//        util.set(key,visitCount.getPv());
//        util.set("post:UV",visitCount.getUv());
//        return visitCount;
//    }

    @Override
    public VisitCount staticUP(String route, HttpServletRequest request) {
        String realIp = ipUtils.getRealIp(request);

        // 先统计访客量
        String key="blog:UV";
        String key2="blog:PV";
        boolean b = util.hasKey(key);
        boolean b2=util.hasKey(key2);
        // 没访问过
        if(b==false||b2==false){
            VisitCount visitCount = mapper.selectById(1);
            // redis中添加值
            util.set("blog:UV",String.valueOf(visitCount.getUv()));
            util.set("blog:PV",String.valueOf(visitCount.getPv()));


        }

        util.sSet("blog:ip",realIp);
        util.sSet("blog:ip:route",realIp+":"+route);

        // 这样应该没问题了吧
        String s = (String) util.get("blog:UV");
        int uv = Integer.parseInt(s)+(int)util.sGetSetSize("blog:ip");
        s = (String) util.get("blog:PV");
        int pv = Integer.parseInt(s)+(int)util.sGetSetSize("blog:ip:route");
        return new VisitCount(1,uv,pv);


    }
}
