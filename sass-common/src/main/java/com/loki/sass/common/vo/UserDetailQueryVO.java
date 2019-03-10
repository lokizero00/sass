package com.loki.sass.common.vo;

import lombok.Data;

@Data
public class UserDetailQueryVO extends BaseQueryVO {

    private String mobile;

    private String nickName;

    private Integer state;

    private String realName;

    private Integer adminId;

    private Integer zoneId;

}
