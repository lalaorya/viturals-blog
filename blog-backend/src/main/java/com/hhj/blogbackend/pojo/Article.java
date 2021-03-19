package com.hhj.blogbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    int id;
    String title;
    String summary;
    String content;
    Date publish_time;
    Date update_time;
    int read_num;
    int like_num;
    String picture_url;
    int top;
    int article_status;
}
