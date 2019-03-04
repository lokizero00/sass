package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class AdminLoginRequestVO {

    @NotNull(message="手机号不允许为空")
    private String mobile;

    @NotNull(message="密码不允许为空")
    private String password;
}
