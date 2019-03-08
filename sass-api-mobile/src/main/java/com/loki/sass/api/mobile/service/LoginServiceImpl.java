package com.loki.sass.api.mobile.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loki.sass.common.code.LoginResultCode;
import com.loki.sass.common.constant.Constants;
import com.loki.sass.common.dto.CurrentUserInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.WechatUserInfoDTO;
import com.loki.sass.common.dto.WechatAuthDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.DesUtil;
import com.loki.sass.domain.mapper.UserAuthMapper;
import com.loki.sass.domain.mapper.UserMapper;
import com.loki.sass.domain.mapper.WechatAuthMapper;
import com.loki.sass.domain.model.User;
import com.loki.sass.domain.model.UserAuth;
import com.loki.sass.domain.model.WechatAuth;
import com.loki.sass.feignclient.feignService.FeignTokenService;
import com.loki.sass.feignclient.feignService.FeignSmsService;
import com.loki.sass.feignclient.feignService.FeignWechatLoginService;
import com.loki.sass.api.mobile.api.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by lokizero00 on 2019-02-12
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserAuthMapper userAuthMapper;
    @Autowired
    FeignTokenService feignTokenService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    FeignSmsService feignSmsService;
    @Autowired
    FeignWechatLoginService feignWechatLoginService;
    @Autowired
    WechatAuthMapper wechatAuthMapper;

    @Transactional
    @Override
    public ResultDTO<String> userLogin(String mobile, String password) throws BizException {
        ResultDTO<String> rs=new ResultDTO<String>();
        if(StringUtils.isEmpty(mobile)){
            throw new BizException(LoginResultCode.LOGIN_MOBILE_NULL_ERROR);
        }
        if (StringUtils.isEmpty(password)) {
            throw new BizException(LoginResultCode.LOGIN_PASSWORD_NULL_ERROR);
        }

        User user=userMapper.selectByMobile(mobile);

        if(null==user){
            log.error("userLogin失败 : 用户不存在,mobile="+mobile);
            throw new BizException(LoginResultCode.LOGIN_USER_INEXIST_ERROR);
        }

        if(!password.equals(user.getPassword())){
            log.error("用户名或密码错误,mobile="+mobile);
            throw new BizException(LoginResultCode.LOGIN_PASSWORD_VERIFY_ERROR);
        }

        UserAuth userAuth=userAuthMapper.selectByUserId(user.getId());

        String secret=storeRedisUserInfo(user,userAuth,Constants.USER_APP_TOKEN);

        try {
            ResultDTO<String> result=feignTokenService.getToken(mobile,secret);
            if(!result.isSuccess()){
                throw new BizException(result.getResultCode());
            }
            rs.setModule(result.getModule());
            rs.setSuccess(true);
            rs.setResultCode(LoginResultCode.TRUE);
        } catch (Exception e) {
            log.error("登录出错",e);
            throw new BizException(LoginResultCode.LOGIN_SERVICE_ERROR);
        }

        return rs;
    }

    @Transactional
    @Override
    public ResultDTO<String> userLoginBySms(String mobile, String smsCode) throws BizException {
        ResultDTO<String> rs=new ResultDTO<String>();
        if(StringUtils.isEmpty(mobile)){
            throw new BizException(LoginResultCode.LOGIN_MOBILE_NULL_ERROR);
        }
        if (StringUtils.isEmpty(smsCode)) {
            throw new BizException(LoginResultCode.LOGIN_SMSCODE_NULL_ERROR);
        }

        ResultDTO<String> codeResult = feignSmsService.confirmSmsCaptcha(mobile,smsCode);
        if (!codeResult.isSuccess()){
            throw new BizException(codeResult.getResultCode());
        }

        User user=userMapper.selectByMobile(mobile);

        if(null==user){
            log.error("userLoginBySms失败 : 用户不存在,mobile="+mobile);
            throw new BizException(LoginResultCode.LOGIN_USER_INEXIST_ERROR);
        }

        UserAuth userAuth=userAuthMapper.selectByUserId(user.getId());

        String secret=storeRedisUserInfo(user,userAuth,Constants.USER_APP_TOKEN);

        try {
            ResultDTO<String> tokenResult=feignTokenService.getToken(mobile,secret);
            if(!tokenResult.isSuccess()){
                throw new BizException(tokenResult.getResultCode());
            }
            rs.setModule(tokenResult.getModule());
            rs.setSuccess(true);
            rs.setResultCode(LoginResultCode.TRUE);
        } catch (Exception e) {
            log.error("登录出错",e);
            throw new BizException(LoginResultCode.LOGIN_SERVICE_ERROR);
        }

        return rs;
    }

    @Transactional
    @Override
    public ResultDTO<WechatAuthDTO> userWechatLoginAuth(String code) throws BizException {
        ResultDTO<WechatAuthDTO> resultDTO = new ResultDTO<>();
        WechatAuthDTO wechatAuthDto = new WechatAuthDTO();
        WechatUserInfoDTO wechatUserInfoDto = feignWechatLoginService.requestWechatLogin(code);

        WechatAuth wechatAuth = wechatAuthMapper.selectWechatAuthByOpenId(DesUtil.encrypt(wechatUserInfoDto.getOpenid()));
        if(wechatAuth == null){//新用户
            wechatAuth = new WechatAuth();
            wechatAuth.setOpenId(DesUtil.encrypt(wechatUserInfoDto.getOpenid()));
            wechatAuthMapper.insertSelective(wechatAuth);
            wechatAuthDto.setIsBind(false);
            wechatAuthDto.setOpenId(wechatAuth.getOpenId());
        }else {
            if(wechatAuth.getUserId() == null){//没有绑定登录账户User
                wechatAuthDto.setIsBind(false);
                wechatAuthDto.setOpenId(wechatAuth.getOpenId());
            }else {
                User user = userMapper.selectByPrimaryKey(wechatAuth.getUserId());
                UserAuth userAuth=userAuthMapper.selectByUserId(user.getId());
                wechatAuthDto.setToken(this.getUserToken(user,userAuth,Constants.USER_APP_TOKEN));
                wechatAuthDto.setIsBind(true);
                resultDTO.setSuccess(true);
            }
        }
        resultDTO.setModule(wechatAuthDto);
        return resultDTO;
    }

    @Override
    public void logout(String mobile) {

    }

    /**
     * 微信登录绑定手机号
     * @param openId
     * @param mobile
     * @param code
     * @return
     */
    @Transactional
    @Override
    public ResultDTO<String> userWechatBindMobile(String openId,String mobile,String smsCode) throws BizException{
        ResultDTO<String> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);

        WechatAuth wechatAuth = wechatAuthMapper.selectWechatAuthByOpenId(openId);
        log.info("微信授权，user_id:"+wechatAuth.getUserId()+",openid:"+wechatAuth.getOpenId());
//        1、验证微信登录的用户是否存在
        if(wechatAuth == null)
            throw new BizException("0002","请先获取微信授权");
        if(wechatAuth.getUserId() != null)
            throw new BizException("0003","该手机号已绑定");
//        2、验证手机号的合法性
        ResultDTO<String> resultCode = feignSmsService.confirmSmsCaptcha(mobile,smsCode);
        if (!resultCode.isSuccess()){
            throw new BizException(resultCode.getResultCode());
        }
//        3、判断是否为新用户
        User user = userMapper.selectByMobile(mobile);
        UserAuth userAuth=null;
        if(user == null){
            user = new User();
            user.setMobile(mobile);
            userMapper.insertSelective(user);
            user = userMapper.selectByPrimaryKey(user.getId());
        }else{
            userAuth=userAuthMapper.selectByUserId(user.getId());
        }
        wechatAuth.setUserId(user.getId());
        wechatAuthMapper.updateByPrimaryKey(wechatAuth);
        resultDTO.setModule(this.getUserToken(user,userAuth,Constants.USER_APP_TOKEN));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @Override
    public ResultDTO<String> userMobileBindWechat(Integer userId, String code) throws BizException {
        ResultDTO<String> resultDO = new ResultDTO<>();
//        1、获取微信信息
        WechatUserInfoDTO wxUserInfoDto = feignWechatLoginService.requestWechatLogin(code);
        String openId = DesUtil.encrypt(wxUserInfoDto.getOpenid());
//        2、判断该微信号是否已绑定了手机号
        WechatAuth wechatAuth = wechatAuthMapper.selectBindWechatAuthByOpenId(openId);
        if(wechatAuth != null && wechatAuth.getUserId() != null)
            throw new BizException("","该微信已绑定手机号");
        else if(wechatAuth != null && wechatAuth.getUserId() == null){
            wechatAuth.setUserId(userId);
            wechatAuthMapper.updateByPrimaryKey(wechatAuth);
        }else {
            wechatAuth = new WechatAuth();
            wechatAuth.setOpenId(openId);
            wechatAuth.setUserId(userId);
            wechatAuthMapper.insert(wechatAuth);
        }
        resultDO.setSuccess(true);
        resultDO.setModule("绑定成功");
        return resultDO;
    }

    public String getUserToken(User user, UserAuth userAuth, String redisKeyPrefix) throws BizException{
        try{
            String secret=this.storeRedisUserInfo(user,userAuth,redisKeyPrefix);
            ResultDTO<String> tokenResult=feignTokenService.getToken(user.getMobile(),secret);
            if(!tokenResult.isSuccess()){
                throw new BizException(tokenResult.getResultCode());
            }
            return tokenResult.getModule();
        }catch(Exception e){
            throw new BizException(LoginResultCode.LOGIN_SERVICE_ERROR);
        }
    }

    private String storeRedisUserInfo(User user,UserAuth userAuth,String redisKeyPrefix) throws BizException{
        //构造token
        String secret = DigestUtils.md5Hex(RandomStringUtils.randomAlphanumeric(10));

        //将token加入到redis中去
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setMobile(user.getMobile());
        currentUserInfo.setAvatarImg(user.getAvatarUrl());
        currentUserInfo.setNickName(user.getNickName());
        currentUserInfo.setRealName(null!=userAuth? userAuth.getRealName():"");
        currentUserInfo.setUserId(user.getId());
        currentUserInfo.setSecret(secret);

        try {
            ObjectMapper mapper = new ObjectMapper();
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            valueOperations.set(redisKeyPrefix+user.getMobile(), mapper.writeValueAsString(currentUserInfo));
        } catch (Exception e) {
            log.error("登录出错",e);
            throw new BizException(LoginResultCode.LOGIN_SERVICE_ERROR);
        }
        return secret;
    }


}
