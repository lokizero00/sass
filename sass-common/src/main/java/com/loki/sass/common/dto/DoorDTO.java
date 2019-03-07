package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-06
 */
@Data
public class DoorDTO {
    private Integer id;

    private String code;

    private String name;

    private String remoteIp;

    private Integer regionId;

    private String regionName;

    private Integer isDeleted;

    private Integer createBy;

    private String createByName;

    private Integer updateBy;

    private String updateByName;

    private Date createTime;

    private Date updateTime;

    private Integer state;
}
