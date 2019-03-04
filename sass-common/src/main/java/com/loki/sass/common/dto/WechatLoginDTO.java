package com.loki.sass.common.dto;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-17
 */
@Data
public class WechatLoginDTO {
    /**接口调用凭证*/
    private String access_token;

     /**access_token接口调用凭证超时时间，单位（秒）*/
    private String expires_in;

     /**用户刷新access_token*/
    private String refresh_token;

     /**授权用户唯一标识*/
    private String openid;

     /**用户授权的作用域，使用逗号（,）分隔*/
    private String scope;

     /**当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段*/
    private String unionid;

     /**当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段*/
    private Integer errcode;

     /**当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段*/
    private String errmsg;
}
