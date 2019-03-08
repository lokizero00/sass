package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-08
 */
@Data
public class DoorRecordDTO {
    private Integer id;

    private Integer doorId;

    private String doorCode;

    private String doorName;

    private String regionName;

    private Integer userId;

    private String userPhone;

    private String userRealName;

    private Date createTime;

    private Integer success;
}
