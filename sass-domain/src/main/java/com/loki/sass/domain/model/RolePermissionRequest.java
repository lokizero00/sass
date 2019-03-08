package com.loki.sass.domain.model;

import lombok.Data;

import java.util.List;

/*
* 封装一个集合,是角色和权限的一对多映射
* */

@Data
public class RolePermissionRequest {

    private Integer roleId;

    private List<Integer> permissionIdsList;
}
