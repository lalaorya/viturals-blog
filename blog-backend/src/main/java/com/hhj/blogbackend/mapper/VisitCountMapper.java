package com.hhj.blogbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.hhj.blogbackend.pojo.VisitCount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
// 这个类太简单了，不写service了，controller直接调用mapper
public interface VisitCountMapper extends BaseMapper<VisitCount> {
}
