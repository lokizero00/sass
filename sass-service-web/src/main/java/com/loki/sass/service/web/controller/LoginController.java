package com.loki.sass.service.web.controller;

import com.loki.sass.common.code.CommonResultCode;
import com.loki.sass.common.code.LoginResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.AdminLoginRequestVO;
import com.loki.sass.service.web.aop.bind.Function;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@RestController
@RequestMapping("/login")
@Function(value ="admin登录管理",moduleName = "登录管理",subModuleName = "")
public class LoginController {

    @CrossOrigin
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<String> adminLogin(@Valid @RequestBody AdminLoginRequestVO adminLoginRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }
        ResultDTO result = new ResultDTO();
        result.setSuccess(false);
        if (StringUtils.isEmpty(adminLoginRequestVO.getMobile())) {
            log.info("用户名不能为空");
            throw new BizException(LoginResultCode.LOGIN_MOBILE_NULL_ERROR);
        }
        if (StringUtils.isEmpty(adminLoginRequestVO.getPassword())) {
            log.info("密码不能为空");
            throw new BizException(LoginResultCode.LOGIN_PASSWORD_NULL_ERROR);
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(adminLoginRequestVO.getMobile(), adminLoginRequestVO.getPassword());
            subject.login(token);
//            ShiroAdmin admin = (ShiroAdmin) subject.getPrincipal();
//            session.setAttribute("admin",admin);
            Session session=SecurityUtils.getSubject().getSession();
            result.setModule(session.getId());
            result.setSuccess(true);
        } catch (UnknownAccountException e) {
            log.error("登录失败：用户名或密码不正确[{}]",adminLoginRequestVO.getMobile());
            throw new BizException(LoginResultCode.LOGIN_PASSWORD_VERIFY_ERROR);
        } catch (IncorrectCredentialsException e) {
            log.info("登录失败：用户名或密码不正确[{}]",adminLoginRequestVO.getMobile());
            throw new BizException(LoginResultCode.LOGIN_PASSWORD_VERIFY_ERROR);
        } catch (BizException e) {
            throw e;
        } catch (RuntimeException e) {
            log.error("登录失败：未知错误,请联系管理员[{}]"+ e,adminLoginRequestVO.getMobile());
            throw new BizException(CommonResultCode.COMMON_SERVICE_ERROR);
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/adminLoginOut", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<String> adminLoginOut()  throws BizException {
        ResultDTO result = new ResultDTO();
        SecurityUtils.getSubject().logout();
        result.setSuccess(true);
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/unauth")
    public String responseMsg(){
        return "failure";
    }



    @CrossOrigin
    @RequestMapping(value = "/nologin")
    public ResultDTO<?> nologin(){
        throw new BizException(CommonResultCode.COMMON_LOGIN_TIMEOUT);
    }
}
