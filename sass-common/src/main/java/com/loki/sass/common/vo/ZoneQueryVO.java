package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class ZoneQueryVO extends BaseQueryVO{

    private String name;

    private String nation;

    private String province;

    private String city;

    private String town;

    private String createByName;

    private String updateByName;

    private Integer state;

    private Integer adminId;
}
