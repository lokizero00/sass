package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class ZoneRequestVO {

    @NotNull(message="小区名称不允许为空")
    private String name;

    private String nation;

    private String province;

    private String city;

    private String town;

    private Integer state;
}
