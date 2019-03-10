package com.loki.sass.api.web.controller.entrance;

import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.aop.bind.Operate;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.DoorRecordQueryVO;
import com.loki.sass.feignclient.feignService.FeignDoorRecordService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * created by lokizero00 on 2019-03-08
 */
@Slf4j
@RestController
@RequestMapping("/doorRecord")
@Function(value ="门禁记录管理",moduleName = "出入管理",subModuleName = "")
public class DoorRecordController {
    @Autowired
    FeignDoorRecordService feignDoorRecordService;

    @ApiOperation(value="门禁记录查询", notes="门禁记录查询，条件查询，adminId默认写0")
    @Operate(value = "记录查询")
    @CrossOrigin
    @RequiresPermissions("doorRecordInfo:view")//权限管理;
    @RequestMapping(value = "/oauth2/getDoorRecordListSearch", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> getDoorRecordListSearch(@Valid @RequestBody DoorRecordQueryVO doorRecordQueryVO, BindingResult bindingResult)  throws BizException {
        if (bindingResult.hasErrors()) {
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        doorRecordQueryVO.setAdminId(shiroAdmin.getId());

        return feignDoorRecordService.getDoorRecordListSearch(JsonUtils.objectToJson(doorRecordQueryVO));
    }
}
