package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhj.blogbackend.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    @Override
    List<Tag> selectList(Wrapper<Tag> queryWrapper);
}
