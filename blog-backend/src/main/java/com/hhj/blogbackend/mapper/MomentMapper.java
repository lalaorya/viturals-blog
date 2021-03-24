package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhj.blogbackend.pojo.Moment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MomentMapper extends BaseMapper<Moment> {
    /**
     * 查询动态总数
     */
    @Override
    Integer selectCount(Wrapper<Moment> queryWrapper);
}
