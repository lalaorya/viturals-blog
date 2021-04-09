package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleComment {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String content;
    private Integer blogId;
    private String nickName;
    private String avatar;
    private String email;
    private String replyNickName;
    private Integer pid;
    private Date createTime;
    private String siteUrl;
    private Integer isCheck;
}
