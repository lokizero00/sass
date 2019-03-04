package com.loki.sass.service.web.controller.admin;

import com.loki.sass.common.code.CommonResultCode;
import com.loki.sass.common.code.LoginResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.AdminLoginRequestVO;
import com.loki.sass.service.web.aop.bind.Function;
import com.loki.sass.service.web.aop.bind.Operate;
import com.loki.sass.service.web.security.realm.ShiroAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/admin")
@Function(value ="admin1登录管理",moduleName = "1登录管理",subModuleName = "")
public class AdminController {

    @Operate(value = "添加管理员")
    @CrossOrigin
    @RequiresPermissions("admin:add")//权限管理;
    @RequestMapping(value = "/oauth2/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addAdmin()  throws BizException {
        ResultDTO result = new ResultDTO();
        ShiroAdmin shiroAdmin = (ShiroAdmin) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        result.setSuccess(true);
        result.setModule(shiroAdmin);
        return result;
    }
}
