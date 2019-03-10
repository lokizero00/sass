package com.loki.sass.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailPO {

    private Integer id;

    private String mobile;

    private String password;

    private String nickName;

    private String avatarUrl;

    private Integer type;

    private Date registryTime;

    private Integer state;

    private String realName;
}
