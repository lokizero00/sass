package com.loki.sass.common.dto;

import lombok.Data;

/**
 * created by lokizero00 on 2019-02-16
 */
@Data
public class WechatAuthDTO {
    /**微信用户的唯一标识*/
    private String openId;

     /**true: 微信用户已绑定手机号 false:未绑定。微信登录接口说明，请求到数据后，请优先判断isBind,然后处理业务逻辑*/
    private Boolean isBind;

     /**用户token：如果用户没有绑定手机号token为空*/
    private String token;
}
