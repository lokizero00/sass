package com.loki.sass.common.vo;

import lombok.Data;

@Data
public class UserRegionQueryVO extends BaseQueryVO {

    private String regionName;

    private String userMobile;

    private String userName;

    private Integer adminId;

    private Integer zoneId;
}
