package com.loki.sass.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionRequest {

    private Integer roleId;

    private List<Integer> permissionIdsList;
}
