package com.loki.sass.api.web.controller.zone;

import com.loki.sass.common.code.RegionResultCode;
import com.loki.sass.common.code.ZoneResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RegionRequestVO;
import com.loki.sass.feignclient.feignService.FeignRegionService;
import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.aop.bind.Operate;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation(value = "创建区域", notes = "创建区域")
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

        return feignRegionService.addRegion(JsonUtils.objectToJson(regionRequestVO));
    }

    @ApiOperation(value = "修改区域", notes = "修改区域")
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

        return feignRegionService.editRegion(JsonUtils.objectToJson(regionRequestVO));
    }

    @ApiOperation(value = "删除区域", notes = "删除区域")
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

        return feignRegionService.deleteRegion(regionId,shiroAdmin.getId());
    }

    @ApiOperation(value = "子区域查询", notes = "根据父级查询子区域")
    @Operate(value = "根据父级查询子区域")
    @CrossOrigin
    @RequiresPermissions("regionInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getRegionListByParentId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getRegionListByParentId(@RequestParam("regionId") final int regionId)  throws BizException {

        if (regionId<=0){
            throw new BizException(RegionResultCode.REGION_ID_INVALID);
        }

        return feignRegionService.getRegionListByParentId(regionId);
    }

    @ApiOperation(value = "根区域查询", notes = "查询区域根列表")
    @Operate(value = "查询区域根列表")
    @CrossOrigin
    @RequiresPermissions("regionInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getRootRegionList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getRootRegionList()  throws BizException {
        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        return feignRegionService.getRootRegionList(shiroAdmin.getId());
    }
}
