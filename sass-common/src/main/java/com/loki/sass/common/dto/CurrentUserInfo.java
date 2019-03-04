package com.loki.sass.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * created by lokizero00 on 2019-02-14
 */
@Data
public class CurrentUserInfo implements Serializable {
    private static final long serialVersionUID = -3434272908589805662L;

    /**用户ID*/
    private Integer userId;

    /**用户名称*/
    private String nickName;

    /**客户真实姓名*/
    private String realName;

    /**用户手机号码*/
    private String mobile;

    /**用户头像地址*/
    private String avatarImg;

    /**用户token密钥*/
    private String secret;
}
