package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-08
 */
@Data
public class DoorRecordPO {
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
