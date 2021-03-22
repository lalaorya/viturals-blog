package com.hhj.blogbackend.shiro;

import lombok.Data;

import java.io.Serializable;


@Data
public class MyProfile implements Serializable {
   private int id;
   private String username;
   private String password;
   private String nick;
   private String avatar;
   private String introduction;
   private String role;

   public int getId(){
       return id;
   }
}
