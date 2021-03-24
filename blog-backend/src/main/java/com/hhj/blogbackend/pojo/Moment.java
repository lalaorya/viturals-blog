package com.hhj.blogbackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moment {
   private Integer id;
   private String content;
   private Date createTime;
   private Integer likeNum;
   private Integer status;
}
