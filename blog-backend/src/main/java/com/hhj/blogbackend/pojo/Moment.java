package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Moment {
   // id自增策略
   @TableId(type=IdType.AUTO)
   private Integer id;
   private String content;
   private Date createTime;
   private Integer likeNum;
   private Integer status;
}
