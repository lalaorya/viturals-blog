package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_tag_relation")
public class ArticleTagRelation {

    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("article_id")
    private Integer articleId;
    @TableField("tag_id")
    private Integer tagId;

    public ArticleTagRelation(Integer articleId,Integer tagId){
        this.articleId=articleId;
        this.tagId=tagId;
    }
}
