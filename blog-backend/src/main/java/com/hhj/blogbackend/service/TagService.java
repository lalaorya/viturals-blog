package com.hhj.blogbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.hhj.blogbackend.pojo.Tag;

import java.io.Serializable;
import java.util.List;

public interface TagService {

    /**
     * 查询所有标签
     */
    List<Tag> selectList();

    /**
     * 根据tag_id查询标签名字
     */
    public List<String > selectTagById(List<Integer> list);

}
