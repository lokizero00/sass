package com.loki.sass.common.dto;

import lombok.Data;

/**
 * 用户与门禁的关系
 */
@Data
public class UserDoorDTO {

    private Integer id;

    private String doorCode;

    private String doorName;

    private String userMobile;

    private String userName;

    private Boolean isPermanent;
}
