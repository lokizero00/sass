package com.loki.sass.service.login.controller;

import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.WechatAuthDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.service.login.api.LoginService;
import com.loki.sass.service.login.bind.annotation.CurrentUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-15
 */

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "用户登录", notes = "用户名密码登录")
    @RequestMapping(value = "v1/userLogin", method = RequestMethod.POST)
    public ResultDTO<String> userLogin(
            @ApiParam(name = "mobile", value = "手机号码") @RequestParam String mobile,
            @ApiParam(name = "password", value = "密码") @RequestParam String password,
            @ApiParam(name = "mobileImei", value = "终端编号") @RequestParam String mobileImei
    ) throws BizException {
        return loginService.userLogin(mobile,password);
    }

    @ApiOperation(value = "用户登录", notes = "短信验证码登录")
    @RequestMapping(value = "v1/userLoginBySms", method = RequestMethod.POST)
    public ResultDTO<String> userLoginBySms(
            @ApiParam(name = "mobile", value = "手机号码") @RequestParam String mobile,
            @ApiParam(name = "smsCode", value = "短信验证码") @RequestParam String smsCode,
            @ApiParam(name = "mobileImei", value = "终端编号") @RequestParam String mobileImei
    ) throws BizException {
        return loginService.userLoginBySms(mobile,smsCode);
    }


    @ApiOperation(value = "用户请求微信登录接口", notes = "微信登录")
    @RequestMapping(value = "/v1/applyUserWechatLogin", method = RequestMethod.POST)
    public ResultDTO<WechatAuthDTO> applyUserWechatLogin(
            @ApiParam(name = "code", value = "微信请求登录的票据") @RequestParam String code) throws BizException {
        log.info("请求微信登录接口，code："+code);
        return loginService.userWechatLoginAuth(code);
    }

    @ApiOperation(value = "用户请求微信登录绑定手机号", notes = "微信登录绑定手机号")
    @RequestMapping(value = "/v1/userWechatBindMobile", method = RequestMethod.POST)
    public ResultDTO<String> userWechatBindMobile(@ApiParam(name = "openId", value = "微信用户的唯一标识",required = true) @RequestParam String openId,
                                         @ApiParam(name = "mobile", value = "用户手机号",required = true) @RequestParam String mobile,
                                         @ApiParam(name = "smsCode", value = "手机验证码",required = true) @RequestParam String smsCode) throws BizException{
        return loginService.userWechatBindMobile(openId,mobile,smsCode);
    }

    @ApiOperation(value = "用户手机号登录绑定微信接口", notes = "手机号登录绑定微信接口")
    @ApiImplicitParams({@ApiImplicitParam(paramType="header",name="token",dataType="String",value="token",required = true,defaultValue="")})
    @RequestMapping(value = "/v1/authUApi/userMobileBindWechat", method = RequestMethod.POST)
    public ResultDTO<String> userMobileBindWechat(@CurrentUser CurrentUserInfo currentUserInfo,
                                         @ApiParam(name = "code", value = "微信请求登录的票据") @RequestParam String code) throws BizException {
        return loginService.userMobileBindWechat(currentUserInfo.getUserId(),code);
    }
}
