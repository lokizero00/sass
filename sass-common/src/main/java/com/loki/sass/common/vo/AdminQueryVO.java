package com.loki.sass.common.vo;

import lombok.Data;

@Data
public class AdminQueryVO extends BaseQueryVO{

    private String username;

    private String createByName;

    private String updateByName;

    private Integer state;

    private Integer adminId;
}
