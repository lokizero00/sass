package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-04
 */
@Data
public class ZoneDTO {
    private Integer id;

    private String name;

    private String nation;

    private String province;

    private String city;

    private String town;

    private Integer createBy;

    private Integer updateBy;

    private String createByName;

    private String updateByName;

    private Date createTime;

    private Date updateTime;

    private Integer isDeleted;

    private Integer state;
}
