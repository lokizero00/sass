package com.loki.sass.common.vo;

import lombok.Data;

@Data
public class UserDoorQueryVO extends BaseQueryVO {

    private String doorName;

    private String userMobile;

    private String userName;

    private Boolean isPermanent;

    private Integer adminId;

    private Integer zoneId;
}
