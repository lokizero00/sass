package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PermissionDTO {
    private Integer id;

    private String name;

    private String resourceType;

    private String url;

    private String permission;

    private Integer parentId;

    private Integer available;

    private Integer createBy;

    private Integer updateBy;

    private Date createTime;

    private Date updateTime;

    private Integer isRegional;

    private Integer isDeleted;
}