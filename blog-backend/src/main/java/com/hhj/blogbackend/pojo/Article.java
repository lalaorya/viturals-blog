package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    // id自增策略
    @TableId(type= IdType.AUTO)
    int id;
    String title;
    String summary;
    String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @TableField("publish_time")
    Date publishTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    Date updateTime;
    int readNum;
    int likeNum;
    String pictureUrl;
    int top;
    int articleStatus;

    // 在dto中新建了数据传输对象，不能用dao对象传，不规范感觉
//
//    /**
//     * 并非表中字段
//     */
//
//    @TableField(exist = false)
//    ArrayList<String> categorys;
//    @TableField(exist = false)
//    ArrayList<String> tags;
}
