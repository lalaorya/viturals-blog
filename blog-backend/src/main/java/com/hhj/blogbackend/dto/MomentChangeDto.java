package com.hhj.blogbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentChangeDto {

    private Integer id;
    private String content;
//    private Integer published;
}
