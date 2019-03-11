package com.loki.sass.common.dto;

import lombok.Data;

/**
 * 用户与门禁的关系
 * 返回:门禁编号,门禁名称,用户手机,用户姓名,授权类型
 */
@Data
public class UserDoorDTO {

    private Integer id;

    private String doorCode;

    private String doorName;

    private String userMobile;

    private String userName;

    private Integer type;
}
