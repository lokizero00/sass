package com.loki.sass.common.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class VisitApplyRequestVO {

    @NotNull(message="姓名不允许为空")
    private String realName;

    @NotNull(message="手机号不允许为空")
    private String mobile;

    @NotNull(message="所属区域不允许为空")
    private Integer regionId;

    @NotNull(message="被访人手机号不允许为空")
    private String intervieweeMobile;

    private String purpose;

    @NotNull(message="来访时间不允许为空")
    private Date visitingTime;

    @NotNull(message="微信授权错误")
    private String openId;

    private String nickName;

    private Integer sex;

    private String nation;

    private String province;

    private String city;

    private String avatarUrl;
}
