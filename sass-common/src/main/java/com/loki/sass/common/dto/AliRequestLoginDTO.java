package com.loki.sass.common.dto;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-16
 */
@Data
public class AliRequestLoginDTO {
    /**支付宝用户的唯一userId*/
    private String aliAccount;

    /**支付宝用户的昵称*/
    private String aliNickName;

    /**支付宝用户的头像url*/
    private String aliAvatar;

    /**访问令牌*/
    private String access_token;

    /**访问令牌的有效时间，单位是秒*/
    private String expires_in;

    /**刷新令牌。通过该令牌可以刷新access_token*/
    private String refresh_token;

    /**刷新令牌的有效时间，单位是秒*/
    private String re_expires_in;
}
