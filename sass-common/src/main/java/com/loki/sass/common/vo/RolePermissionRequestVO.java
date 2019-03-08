package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RolePermissionRequestVO {

    private Integer id;

    @NotNull(message="权限id不能为空")
    private Integer permissionId;

    @NotNull(message="角色id不能为空")
    private Integer roleId;
}
