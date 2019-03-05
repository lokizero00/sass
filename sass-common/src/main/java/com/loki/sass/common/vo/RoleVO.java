package com.loki.sass.common.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class RoleVO implements Serializable {

    private Integer id;

    @NotNull(message = "地区id不能为空")
    private Integer zoneId;

    @NotNull(message = "角色名称不能为空")
    private String role;

    private String description;
}
