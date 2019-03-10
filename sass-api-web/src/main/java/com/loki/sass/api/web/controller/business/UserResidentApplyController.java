package com.loki.sass.api.web.controller.business;

import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.UserResidentApplyQueryVO;
import com.loki.sass.common.vo.UserResidentApplyVerifyVO;
import com.loki.sass.feignclient.feignService.FeignUserResidentApplyService;
import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.aop.bind.Operate;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Function(value ="业主入驻申请管理",moduleName = "业务管理",subModuleName = "")
@Api(tags="业主入驻申请管理")
public class UserResidentApplyController {
    @Autowired
    FeignUserResidentApplyService feignUserResidentApplyService;

    @ApiOperation(value="业主入驻申请查询", notes="业主入驻申请查询，条件查询，adminId默认写0")
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

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        userResidentApplyQueryVO.setAdminId(shiroAdmin.getId());

        return feignUserResidentApplyService.getApplyListSearch(JsonUtils.objectToJson(userResidentApplyQueryVO));
    }

    @ApiOperation(value="业主入驻申请审批", notes="业主入驻申请审批")
    @Operate(value = "申请审批")
    @CrossOrigin
    @RequiresPermissions("userResidentApplyInfo:verify")//权限管理;
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
