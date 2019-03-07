package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class RolePO {
    private Integer id;

    private Integer zoneId;

    private String role;

    private String description;

    private Integer available;

    private Integer createBy;

    private Integer updateBy;

    private String createByName;

    private String updateByName;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}
