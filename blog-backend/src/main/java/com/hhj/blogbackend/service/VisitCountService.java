package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.pojo.VisitCount;

import javax.servlet.http.HttpServletRequest;

public interface VisitCountService extends IService<VisitCount>  {

//    VisitCount getPVUV();

    VisitCount staticUP(String route, HttpServletRequest request);
}
