package com.hhj.blogbackend.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 前台博客首页信息展示
 */
public class ArticleInfo {

    int id;
    String title;
    String summary;
//    @TableField("content")
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date publishTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date updateTime;
    int readNum;
    int likeNum;
    String pictureUrl;
    int top;
    int articleStatus;

    List<Map<String,Object>> categorys;
    List<Map<String,Object>> tags;
}
