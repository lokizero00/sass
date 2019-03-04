package com.loki.sass.service.payment.api;

import com.alipay.api.AlipayApiException;
import com.loki.sass.common.dto.AliRequestLoginDTO;
import com.loki.sass.common.exception.BizException;

/**
 * created by lokizero00 on 2019-02-18
 */
public interface AliPayLoginService {
    /**
     * 获取支付宝签名
     * @return
     */
    public String getAlipaySign() throws BizException;
    /**
     * 获取支付宝登录令牌
     * @param userId    支付宝用户的唯一userId
     * @param code  授权码
     * @return
     * @throws AlipayApiException
     */
    public AliRequestLoginDTO requestAliLoginAuth(String userId, String code) throws BizException;
}
