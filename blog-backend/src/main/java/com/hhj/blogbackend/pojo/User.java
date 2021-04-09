package com.hhj.blogbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    @TableId(type= IdType.AUTO)
    Integer id;
    String username;
    String password;
    String nick;//昵称
    String avatar;
    String introduction;
    String role;
}
