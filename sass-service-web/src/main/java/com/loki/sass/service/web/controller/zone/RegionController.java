package com.loki.sass.service.web.controller.zone;

import com.loki.sass.common.code.PropertyResultCode;
import com.loki.sass.common.code.RegionResultCode;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RegionRequestVO;
import com.loki.sass.feignclient.feignService.FeignRegionService;
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
import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/region")
@Function(value ="区域管理",moduleName = "小区管理",subModuleName = "")
public class RegionController {
    @Autowired
    FeignRegionService feignRegionService;

    @Operate(value = "创建区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:add")//权限管理;
    @RequestMapping(value = "/oauth2/addRegion", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addRegion(@Valid @RequestBody RegionRequestVO regionRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (StringUtils.isEmpty(regionRequestVO.getName())
                || regionRequestVO.getZoneId()<=0
                || regionRequestVO.getPropertyId()<=0
                || regionRequestVO.getType()<=0){
            throw new BizException(RegionResultCode.REGION_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        regionRequestVO.setCreateBy(shiroAdmin.getId());

        ResultDTO result = new ResultDTO<>();
        result.setSuccess(false);
        if(feignRegionService.addRegion(JsonUtils.objectToJson(regionRequestVO))){
            result.setSuccess(true);
        }
        return result;
    }

    @Operate(value = "修改区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:edit")//权限管理;
    @RequestMapping(value = "/oauth2/editRegion", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> editRegion(@Valid @RequestBody RegionRequestVO regionRequestVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("修改失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        if (regionRequestVO.getId()<=0
                || StringUtils.isEmpty(regionRequestVO.getName())
                || regionRequestVO.getZoneId()<=0
                || regionRequestVO.getPropertyId()<=0
                || regionRequestVO.getType()<=0){
            throw new BizException(RegionResultCode.REGION_DATA_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        regionRequestVO.setUpdateBy(shiroAdmin.getId());

        ResultDTO result = new ResultDTO<>();
        result.setSuccess(false);
        if(feignRegionService.editRegion(JsonUtils.objectToJson(regionRequestVO))){
            result.setSuccess(true);
        }
        return result;
    }

    @Operate(value = "删除区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:delete")//权限管理;
    @RequestMapping(value = "/oauth2/deleteRegion", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> deleteRegion(@RequestParam("regionId") final int regionId)  throws BizException {

        if (regionId<=0){
            throw new BizException(RegionResultCode.REGION_ID_INVALID);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();

        ResultDTO result = new ResultDTO<>();
        result.setSuccess(false);
        if(feignRegionService.deleteRegion(regionId,shiroAdmin.getId())){
            result.setSuccess(true);
        }
        return result;
    }

    @Operate(value = "根据父级查询子区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getRegionListByParentId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getRegionListByParentId(@RequestParam("regionId") final int regionId)  throws BizException {

        if (regionId<=0){
            throw new BizException(RegionResultCode.REGION_ID_INVALID);
        }

        ResultDTO result = new ResultDTO<>();
        List<RegionDTO> list=feignRegionService.getRegionListByParentId(regionId);
        result.setModule(list);
        result.setSuccess(true);
        return result;
    }

    @Operate(value = "根据小区ID查询区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getRootRegionListByZoneId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getRootRegionListByZoneId(@RequestParam("zoneId") final int zoneId)  throws BizException {
        if (zoneId<=0){
            throw new BizException(ZoneResultCode.ZONE_ID_INVALID);
        }

        ResultDTO result = new ResultDTO<>();
        List<RegionDTO> list=feignRegionService.getRootRegionListByZoneId(zoneId);
        result.setModule(list);
        result.setSuccess(true);
        return result;
    }

    @Operate(value = "根据物业ID查询区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getRootRegionListByPropertyId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getRootRegionListByPropertyId(@RequestParam("propertyId") final int propertyId)  throws BizException {
        if (propertyId<=0){
            throw new BizException(PropertyResultCode.PROPERTY_ID_INVALID);
        }

        ResultDTO result = new ResultDTO<>();
        List<RegionDTO> list=feignRegionService.getRootRegionListByPropertyId(propertyId);
        result.setModule(list);
        result.setSuccess(true);
        return result;
    }
}
