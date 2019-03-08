package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class RolePermissionRequestVO implements Serializable {

    @NotNull(message="角色id不能为空")
    private Integer roleId;

    @NotNull(message="权限id不能为空")
    private List<Integer> permissionIdsList;
}
