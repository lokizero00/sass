package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class PropertyRequestVO {
    private Integer id;

    @NotNull(message="物业名称不允许为空")
    private String name;

    @NotNull(message="物业负责人姓名不允许为空")
    private String managerName;

    @NotNull(message="物业负责人手机号不允许为空")
    private String managerPhone;

    @NotNull(message="所属小区不允许为空")
    private Integer zoneId;

    private Integer createBy;

    private Integer updateBy;

    private Integer state;

}
