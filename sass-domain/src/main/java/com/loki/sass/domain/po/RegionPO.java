package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-05
 */
@Data
public class RegionPO {
    private Integer id;

    private String name;

    private String description;

    private Integer zoneId;

    private String zoneName;

    private Integer propertyId;

    private String propertyName;

    private Integer type;

    private Integer floor;

    private Integer parentId;

    private Integer createBy;

    private String createByName;

    private Integer updateBy;

    private String updateByName;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;
}
