package com.loki.sass.service.web.controller.zone;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.ResidentResultCode;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.common.vo.ZoneRequestVO;
import com.loki.sass.feignclient.feignService.FeignZoneService;
import com.loki.sass.service.web.aop.bind.Function;
import com.loki.sass.service.web.aop.bind.Operate;
import com.loki.sass.service.web.security.realm.ShiroAdmin;
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
@RequestMapping("/zone")
@Function(value ="小区管理",moduleName = "小区管理",subModuleName = "")
public class ZoneController {
    @Autowired
    FeignZoneService feignZoneService;

    @Operate(value = "小区查询")
    @CrossOrigin
    @RequiresPermissions("zoneInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getZoneListSearch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getZoneListByPage(@Valid @RequestBody ZoneQueryVO zoneQueryVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ResultDTO<PageInfo<ZoneDTO>> result = new ResultDTO<>();
        PageInfo<ZoneDTO> list=feignZoneService.getZoneListSearch(JsonUtils.objectToJson(zoneQueryVO));
        result.setSuccess(true);
        result.setModule(list);
        return result;
    }

    @Operate(value = "创建小区")
    @CrossOrigin
    @RequiresPermissions("zoneInfo:add")//权限管理;
    @RequestMapping(value = "/oauth2/addZone", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addZone(@Valid @RequestBody ZoneRequestVO zoneRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (StringUtils.isEmpty(zoneRequestVO.getName())){
            throw new BizException(ZoneResultCode.ZONE_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        zoneRequestVO.setCreateBy(shiroAdmin.getId());

        ResultDTO result = new ResultDTO<>();
        result.setSuccess(false);
        if(feignZoneService.addZone(JsonUtils.objectToJson(zoneRequestVO))){
            result.setSuccess(true);
        }
        return result;
    }

    @Operate(value = "修改小区")
    @CrossOrigin
    @RequiresPermissions("zoneInfo:edit")//权限管理;
    @RequestMapping(value = "/oauth2/editZone", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> editZone(@Valid @RequestBody ZoneRequestVO zoneRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("修改失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (zoneRequestVO.getId()<=0
                || StringUtils.isEmpty(zoneRequestVO.getName())){
            throw new BizException(ZoneResultCode.ZONE_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        zoneRequestVO.setUpdateBy(shiroAdmin.getId());

        ResultDTO result = new ResultDTO<>();
        result.setSuccess(false);
        if(feignZoneService.editZone(JsonUtils.objectToJson(zoneRequestVO))){
            result.setSuccess(true);
        }
        return result;
    }

    @Operate(value = "删除小区")
    @CrossOrigin
    @RequiresPermissions("zoneInfo:delete")//权限管理;
    @RequestMapping(value = "/oauth2/deleteZone", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> deleteZone(@RequestParam("zoneId") final int zoneId)  throws BizException {

        if (zoneId<=0){
            throw new BizException(ZoneResultCode.ZONE_ID_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();

        ResultDTO result = new ResultDTO<>();
        result.setSuccess(false);
        if(feignZoneService.deleteZone(zoneId,shiroAdmin.getId())){
            result.setSuccess(true);
        }
        return result;
    }
}
