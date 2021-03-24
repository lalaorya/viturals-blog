package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
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

    /**
     * 并非表中字段
     */

    @TableField(exist = false)
    ArrayList<String> categorys;
    @TableField(exist = false)
    ArrayList<String> tags;
}
