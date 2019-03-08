package com.loki.sass.domain.model;

import lombok.Data;

import java.util.List;

/*
 * 封装一个集合,是管理员与角色的一对多映射
 * */

@Data
public class AdminRoleRequest {

    private Integer adminId;

    private List<Integer> roleIdsList;
}
