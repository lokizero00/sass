package com.loki.sass.domain.po;

import lombok.Data;

@Data
public class UserDoorPO {

    private Integer id;

    private String doorCode;

    private String doorName;

    private String userMobile;

    private String userName;

    private Integer isPermanent;
}
