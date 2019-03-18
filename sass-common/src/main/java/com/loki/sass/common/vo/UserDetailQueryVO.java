package com.loki.sass.common.vo;

import lombok.Data;

@Data
public class UserDetailQueryVO extends BaseQueryVO {

    private String mobile;

    private String nickName;

    private Integer type;

    private String realName;

    private Integer state;

    private Integer adminId;

    private String regionName;

    private String zoneName;

}
