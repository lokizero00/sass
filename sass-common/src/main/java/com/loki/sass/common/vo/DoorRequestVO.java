package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by lokizero00 on 2019-03-06
 */
@Data
public class DoorRequestVO {
    private Integer id;

    @NotNull(message="门禁代码不允许为空")
    private String code;

    @NotNull(message="门禁名称不允许为空")
    private String name;

    @NotNull(message="门禁IP不允许为空")
    private String remoteIp;

    @NotNull(message="门禁区域不允许为空")
    private Integer regionId;

    private Integer createBy;

    private Integer updateBy;

    private Integer state;
}
