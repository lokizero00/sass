package com.loki.sass.service.payment.api;

import com.loki.sass.common.dto.WechatUserInfoDTO;
import com.loki.sass.common.exception.BizException;

/**
 * created by lokizero00 on 2019-02-17
 */
public interface WechatLoginService {
    public WechatUserInfoDTO requestWechatLogin(String code) throws BizException;
}
