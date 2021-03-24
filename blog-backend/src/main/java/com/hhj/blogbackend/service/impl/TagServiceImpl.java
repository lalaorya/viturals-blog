package com.hhj.blogbackend.service.impl;

import com.hhj.blogbackend.mapper.TagMapper;
import com.hhj.blogbackend.pojo.Tag;
import com.hhj.blogbackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
