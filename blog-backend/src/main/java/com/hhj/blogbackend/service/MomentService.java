package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhj.blogbackend.pojo.Moment;

public interface MomentService extends IService<Moment> {
    /**
     * 动态总数
     * @return
     */
    public Integer selectCount();
}
