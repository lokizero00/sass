package com.loki.sass.common.vo;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class PropertyQueryVO extends BaseQueryVO{

    private String name;

    private String managerName;

    private String managerPhone;

    private String zoneName;

    private Integer zoneId;

    private String createByName;

    private String updateByName;

    private Integer state;

    private Integer adminId;
}
