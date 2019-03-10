package com.loki.sass.common.vo;

import lombok.Data;

@Data
public class RoleQueryVO extends BaseQueryVO {

    private String role;

    private String createByName;

    private String updateByName;

    private Integer adminId;
}
