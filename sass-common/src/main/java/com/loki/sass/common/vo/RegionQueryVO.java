package com.loki.sass.common.vo;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class RegionQueryVO extends BaseQueryVO{
    private String name;

    private String description;

    private Integer zoneId;

    private String zoneName;

    private Integer propertyId;

    private String propertyName;

    private Integer type;

    private Integer floor;

    private Integer parentId;

    private String createByName;

    private String updateByName;
}
