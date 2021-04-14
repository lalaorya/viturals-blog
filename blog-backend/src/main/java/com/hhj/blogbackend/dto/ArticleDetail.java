package com.hhj.blogbackend.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Date;

/**
 *  文章细节字段
 *  增加了所属分类和标签字段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("articleDetail")
public class ArticleDetail {
    int id;
    String title;
    String summary;
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

    ArrayList<String> categorys;
    ArrayList<String> tags;

}
