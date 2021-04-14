package com.hhj.blogbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 根据标签序号绑定分页信息
 */
@Data
@AllArgsConstructor
public class TagQuery {

    private Integer id;
    private Integer currentPage;
    private Integer pageSize;
}
