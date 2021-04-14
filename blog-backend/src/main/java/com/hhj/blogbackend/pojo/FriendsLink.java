package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendsLink {

    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private String info;
    private String avatar;
    @TableField("is_check")
    private Integer isCheck;
}
