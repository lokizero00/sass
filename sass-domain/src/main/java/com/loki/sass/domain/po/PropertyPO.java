package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-05
 */
@Data
public class PropertyPO {
    private Integer id;

    private String name;

    private String managerName;

    private String managerPhone;

    private Integer zoneId;

    private String zoneName;

    private Integer createBy;

    private Integer updateBy;

    private String createByName;

    private String updateByName;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    private Integer state;
}
