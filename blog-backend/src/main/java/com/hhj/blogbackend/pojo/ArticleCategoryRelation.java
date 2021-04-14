package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_category_relation")
public class ArticleCategoryRelation {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer articleId;
    private Integer categoryId;

    public ArticleCategoryRelation(Integer articleId,Integer categoryId){
        this.articleId=articleId;
        this.categoryId=categoryId;
    }
}
