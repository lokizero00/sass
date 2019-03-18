package com.loki.sass.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDetailQueryVO extends BaseQueryVO {

    private String mobile;

    private String nickName;

    private Integer type;

    private String realName;

    private Integer state;

    private Integer adminId;

    @JsonIgnore
    private Integer regionId;

    private String regionName;

    @JsonIgnore
    private Integer zoneId;

    private String zoneName;

}
