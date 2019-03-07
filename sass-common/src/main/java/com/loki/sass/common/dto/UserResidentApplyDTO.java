package com.loki.sass.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * created by lokizero00 on 2019-03-07
 */
@Data
public class UserResidentApplyDTO {
    private Integer id;

    private Integer userId;

    private String userRealName;

    private String userPhone;

    private String regionName;

    private Integer regionId;

    private Date createTime;

    private Date updateTime;

    private Integer updateBy;

    private String updateByName;

    private Integer state;
}
