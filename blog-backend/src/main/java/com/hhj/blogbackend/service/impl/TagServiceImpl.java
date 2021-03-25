package com.hhj.blogbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhj.blogbackend.mapper.TagMapper;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import com.hhj.blogbackend.pojo.Tag;
import com.hhj.blogbackend.service.TagService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper mapper;
    @Override
    public List<Tag> selectList() {
        List<Tag> tags = mapper.selectList(null);
        return tags;
    }

    /**
     * 根据标签id(duo ge_)查询标签名
     * @param list
     * @return
     */
    @Override
    public List<String> selectTagById(List<Integer> list) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.select("name")
                .in("id",list);
        List<Tag> tags = mapper.selectList(wrapper);
        List<String> res = new ArrayList<>();
        for(Tag a:tags){
            res.add(a.getName());
        }
        return res;
    }
}
