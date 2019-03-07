package com.loki.sass.service.web.controller.business;

import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.UserResidentApplyQueryVO;
import com.loki.sass.common.vo.UserResidentApplyVerifyVO;
import com.loki.sass.feignclient.feignService.FeignUserResidentApplyService;
import com.loki.sass.service.web.aop.bind.Function;
import com.loki.sass.service.web.aop.bind.Operate;
import com.loki.sass.service.web.security.realm.ShiroAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@RestController
@RequestMapping("/userResidentApply")
@Function(value ="业主入驻管理",moduleName = "业务管理",subModuleName = "")
public class UserResidentApplyController {
    @Autowired
    FeignUserResidentApplyService feignUserResidentApplyService;

    @Operate(value = "申请查询")
    @CrossOrigin
    @RequiresPermissions("userResidentApplyInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getApplyListSearch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getApplyListSearch(@Valid @RequestBody UserResidentApplyQueryVO userResidentApplyQueryVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        return feignUserResidentApplyService.getApplyListSearch(JsonUtils.objectToJson(userResidentApplyQueryVO));
    }

    @Operate(value = "申请审批")
    @CrossOrigin
    @RequiresPermissions("userResidentApplyInfo:edit")//权限管理;
    @RequestMapping(value = "/oauth2/applyVerify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> applyVerify(@Valid @RequestBody UserResidentApplyVerifyVO userResidentApplyVerifyVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("审批失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (userResidentApplyVerifyVO.getApplyId()<=0
                || userResidentApplyVerifyVO.getVerifyResult()<=0){
            throw new BizException(ResidentResultCode.REGISTER_VERIFY_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        userResidentApplyVerifyVO.setUpdateBy(shiroAdmin.getId());

        return feignUserResidentApplyService.applyVerify(JsonUtils.objectToJson(userResidentApplyVerifyVO));
    }
}
