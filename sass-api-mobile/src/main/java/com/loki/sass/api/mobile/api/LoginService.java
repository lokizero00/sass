package com.loki.sass.api.mobile.api;

import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.WechatAuthDTO;
import com.loki.sass.common.exception.BizException;

/**
 * created by lokizero00 on 2019-02-12
 */
public interface LoginService {
    public ResultDTO<String> userLogin(String mobile, String password) throws BizException;
    public ResultDTO<String> userLoginBySms(String mobile, String smsCode) throws BizException;
    public ResultDTO<WechatAuthDTO> userWechatLoginAuth(String code) throws BizException;
    public ResultDTO<String> userWechatBindMobile(String openId,String mobile,String smsCode) throws BizException;
    public ResultDTO<String> userMobileBindWechat(Integer userId,String code) throws BizException;
    public void logout(final String mobile);
}
