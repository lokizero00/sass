package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class ResidentJoinRequestVO {

    @NotNull(message="姓名不允许为空")
    private String realName;

    @NotNull(message="手机号不允许为空")
    private String mobile;

    @NotNull(message="所属区域不允许为空")
    private Integer regionId;

    @NotNull(message="微信授权错误")
    private String openId;

    private String nickName;

    private Integer sex;

    private String nation;

    private String province;

    private String city;

    private String avatarUrl;
}
