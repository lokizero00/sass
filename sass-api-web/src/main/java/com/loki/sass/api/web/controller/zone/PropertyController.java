package com.loki.sass.api.web.controller.zone;

import com.loki.sass.common.code.PropertyResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.PropertyQueryVO;
import com.loki.sass.common.vo.PropertyRequestVO;
import com.loki.sass.feignclient.feignService.FeignPropertyService;
import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.aop.bind.Operate;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/property")
@Function(value ="物业管理",moduleName = "小区管理",subModuleName = "")
public class PropertyController {
    @Autowired
    FeignPropertyService feignPropertyService;

    @Operate(value = "物业查询")
    @CrossOrigin
    @RequiresPermissions("propertyInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getPropertyListSearch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getPropertyListSearch(@Valid @RequestBody PropertyQueryVO propertyQueryVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        propertyQueryVO.setAdminId(shiroAdmin.getId());

        return feignPropertyService.getPropertyListSearch(JsonUtils.objectToJson(propertyQueryVO));
    }

    @Operate(value = "创建物业")
    @CrossOrigin
    @RequiresPermissions("propertyInfo:add")//权限管理;
    @RequestMapping(value = "/oauth2/addProperty", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addProperty(@Valid @RequestBody PropertyRequestVO propertyRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (StringUtils.isEmpty(propertyRequestVO.getName())
                || propertyRequestVO.getZoneId()<=0){
            throw new BizException(PropertyResultCode.PROPERTY_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        propertyRequestVO.setCreateBy(shiroAdmin.getId());

        return feignPropertyService.addProperty(JsonUtils.objectToJson(propertyRequestVO));
    }

    @Operate(value = "修改物业")
    @CrossOrigin
    @RequiresPermissions("propertyInfo:edit")//权限管理;
    @RequestMapping(value = "/oauth2/editProperty", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> editProperty(@Valid @RequestBody PropertyRequestVO propertyRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("修改失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (propertyRequestVO.getId()<=0
                || StringUtils.isEmpty(propertyRequestVO.getName())){
            throw new BizException(PropertyResultCode.PROPERTY_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        propertyRequestVO.setUpdateBy(shiroAdmin.getId());

        return feignPropertyService.editProperty(JsonUtils.objectToJson(propertyRequestVO));
    }

    @Operate(value = "删除物业")
    @CrossOrigin
    @RequiresPermissions("propertyInfo:delete")//权限管理;
    @RequestMapping(value = "/oauth2/deleteProperty", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> deleteProperty(@RequestParam("propertyId") final int propertyId)  throws BizException {

        if (propertyId<=0){
            throw new BizException(PropertyResultCode.PROPERTY_ID_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();

        return feignPropertyService.deleteProperty(propertyId,shiroAdmin.getId());
    }
}
