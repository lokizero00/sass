package com.loki.sass.common.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class PermissionVO implements Serializable {

    private Integer id;

    @NotNull(message = "权限名称不允许为空")
    private String name;

    @NotNull(message = "资源类型不允许为空")
    private String resourceType;

    @NotNull(message = "url不允许为空")
    private String url;

    private String permission;

    @NotNull(message = "父权限id不允许为空")
    private Integer parentId;

    @NotNull(message = "区域性标志不允许为空")
    private Integer isRegional;
}
