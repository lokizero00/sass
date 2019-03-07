package com.loki.sass.common.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@ToString
public class AdminRequestVO implements Serializable {

    private Integer id;

    @NotNull(message = "用户名不允许为空")
    private String userName;

    @NotNull(message = "真实姓名不允许为空")
    private String realName;

    @NotNull(message = "头像不允许为空")
    private String avatarUrl;

    @NotNull(message = "手机号不允许为空")
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}",message = "手机号格式有误")
    private String mobile;

    @NotNull(message = "密码不允许为空")
    private String password;

    private Integer createBy;

    private Integer updateBy;

}
