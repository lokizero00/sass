package com.loki.sass.api.web.controller.business;

import com.loki.sass.common.code.VisitorResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.VisitorApplyQueryVO;
import com.loki.sass.common.vo.VisitorApplyVerifyVO;
import com.loki.sass.feignclient.feignService.FeignVisitorApplyService;
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
@RequestMapping("/visitorApply")
@Function(value ="访客申请管理",moduleName = "业务管理",subModuleName = "")
@Api(tags="访客申请管理")
public class VisitorApplyController {
    @Autowired
    FeignVisitorApplyService feignVisitorApplyService;

    @ApiOperation(value="访客申请查询", notes="访客申请查询，条件查询，adminId默认写0")
    @Operate(value = "申请查询")
    @CrossOrigin
    @RequiresPermissions("visitorApplyInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getApplyListSearch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getApplyListSearch(@Valid @RequestBody VisitorApplyQueryVO visitorApplyQueryVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        visitorApplyQueryVO.setAdminId(shiroAdmin.getId());

        return feignVisitorApplyService.getApplyListSearch(JsonUtils.objectToJson(visitorApplyQueryVO));
    }

    @ApiOperation(value="访客申请审批", notes="访客申请审批")
    @Operate(value = "申请审批")
    @CrossOrigin
    @RequiresPermissions("visitorApplyInfo:verify")//权限管理;
    @RequestMapping(value = "/oauth2/applyVerify", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> applyVerify(@Valid @RequestBody VisitorApplyVerifyVO visitorApplyVerifyVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("审批失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (visitorApplyVerifyVO.getApplyId()<=0
                || visitorApplyVerifyVO.getVerifyResult()<=0){
            throw new BizException(VisitorResultCode.VISIT_VERIFY_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        visitorApplyVerifyVO.setUpdateBy(shiroAdmin.getId());

        return feignVisitorApplyService.applyVerify(JsonUtils.objectToJson(visitorApplyVerifyVO));
    }
}
