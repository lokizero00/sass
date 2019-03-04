package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {
    private Integer id;

    private Integer zoneId;

    private String role;

    private String description;

    private Integer available;

    private Integer createBy;

    private Integer updateBy;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}