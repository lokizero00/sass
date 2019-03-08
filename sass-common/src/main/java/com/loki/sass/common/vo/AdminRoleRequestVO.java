package com.loki.sass.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminRoleRequestVO implements Serializable {

    private Integer adminId;

    private List<Integer> roleIdsList;
}
