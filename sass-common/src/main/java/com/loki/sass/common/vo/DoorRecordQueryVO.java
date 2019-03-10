package com.loki.sass.common.vo;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-08
 */
@Data
public class DoorRecordQueryVO extends BaseQueryVO{
    private Integer doorId;

    private String doorCode;

    private String doorName;

    private Integer regionId;

    private String regionName;

    private Integer userId;

    private String userPhone;

    private String userRealName;

    private Date createTimeStart;

    private Date createTimeEnd;

    private Integer success;

    private Integer adminId;
}
