package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class AdminPO {
    private Integer id;

    private String userName;

    private String realName;

    private String avatarUrl;

    private String mobile;

    private String password;

    private String salt;

    private Integer isSuper;

    private Integer state;

    private Integer createBy;

    private Integer updateBy;

    private String createByName;

    private String updateByName;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    private Integer zoneId;

    private Integer propertyId;
}
