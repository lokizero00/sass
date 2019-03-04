package com.loki.sass.service.web.controller.zone;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.ZoneDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.ZoneQueryVO;
import com.loki.sass.feignclient.feignService.FeignZoneService;
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
}
