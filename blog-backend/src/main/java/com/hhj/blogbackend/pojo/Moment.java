package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Moment {
   // id自增策略
   @TableId(type=IdType.AUTO)
   private Integer id;
   private String content;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
   private Date createTime;
   private Integer likeNum;
   private Integer status;


}
