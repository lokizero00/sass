package com.loki.sass.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * created by lokizero00 on 2019-02-15
 */
@Data
public class AdminDTO implements Serializable {
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

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    private Integer zoneId;

    private Integer propertyId;
}
