package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class RegionRequestVO {
    private Integer id;

    @NotNull(message="区域名称不允许为空")
    private String name;

    private String description;

    @NotNull(message="所属小区不允许为空")
    private Integer zoneId;

    @NotNull(message="所属物业不允许为空")
    private Integer propertyId;

    @NotNull(message="区域类型不允许为空")
    private Integer type;

    private Integer floor;

    private Integer parentId;

    private Integer createBy;

    private Integer updateBy;
}
