package com.loki.sass.common.vo;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class DoorQueryVO extends BaseQueryVO{

    private String code;

    private String name;

    private String remoteIp;

    private String regionName;

    private Integer regionId;

    private String createByName;

    private String updateByName;

    private Integer state;

    private Integer adminId;
}
