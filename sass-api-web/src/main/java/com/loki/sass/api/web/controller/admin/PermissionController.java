package com.loki.sass.api.web.controller.admin;

import com.loki.sass.api.web.security.realm.ShiroAdmin;
import com.loki.sass.common.code.PermissionResultCode;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.PermissionRequestVO;
import com.loki.sass.feignclient.feignService.FeignPermissionService;
import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.aop.bind.Operate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/permission")
@Function(value ="权限管理",moduleName = "1登录管理",subModuleName = "")
@Api(tags="权限管理")
public class PermissionController {

    @Autowired
    private FeignPermissionService feignPermissionService;

    @ApiOperation(value="添加权限", notes="添加权限")
    @Operate(value = "添加权限")
    @CrossOrigin
    @RequiresPermissions("permission:add")//权限管理;
    @RequestMapping(value = "/oauth2/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> addPermission(@Valid @RequestBody PermissionRequestVO permissionRequestVO, BindingResult bindingResult)  throws BizException {
        if(bindingResult.hasErrors()){
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }
        if (StringUtils.isEmpty(permissionRequestVO.getName())){
            throw new BizException(PermissionResultCode.PERMISSION_NAME_EMPTY);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        permissionRequestVO.setCreateBy(shiroAdmin.getId());

        return feignPermissionService.addPermission(JsonUtils.objectToJson(permissionRequestVO),shiroAdmin.getId());
    }

    @ApiOperation(value="修改权限", notes="修改权限")
    @Operate(value = "修改权限")
    @CrossOrigin
    @RequiresPermissions("permission:edit")//权限管理;
    @RequestMapping(value = "/oauth2/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> editPermission(@Valid @RequestBody PermissionRequestVO permissionRequestVO, BindingResult bindingResult)  throws BizException {
        if(bindingResult.hasErrors()){
            String message = String.format("修改失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }
        if (StringUtils.isEmpty(permissionRequestVO.getName())){
            throw new BizException(PermissionResultCode.PERMISSION_NAME_EMPTY);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        permissionRequestVO.setUpdateBy(shiroAdmin.getId());


        return feignPermissionService.editPermission(JsonUtils.objectToJson(permissionRequestVO),shiroAdmin.getId());
    }

    @ApiOperation(value="删除权限", notes="删除权限")
    @Operate(value = "删除权限")
    @CrossOrigin
    @RequiresPermissions("permission:delete")//权限管理;
    @RequestMapping(value = "/oauth2/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> deletePermission(@RequestParam("id")Integer id)  throws BizException {

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();

        return feignPermissionService.deletePermission(id,shiroAdmin.getId());
    }

    @ApiOperation(value="查看角色拥有权限", notes="查看角色拥有权限")
    @Operate(value = "查看角色拥有权限")
    @CrossOrigin
    @RequiresPermissions("permission:view")//权限管理;
    @RequestMapping(value = "/oauth2/selectByRoleId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<PermissionDTO>> findByRoleId(@RequestParam("roleId")Integer roleId)  throws BizException {

        return feignPermissionService.selectByRoleId(roleId);
    }

    @ApiOperation(value="查看所有权限", notes="查看所有权限")
    @Operate(value = "查看所有权限")
    @CrossOrigin
    @RequiresPermissions("permission:view")//权限管理;
    @RequestMapping(value = "/oauth2/findAll", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<PermissionDTO>> findAll()  throws BizException {

        return feignPermissionService.findAll();
    }

    @ApiOperation(value="通过id查看单个权限", notes="通过id查看单个权限")
    @Operate(value = "通过id查看单个权限")
    @CrossOrigin
    @RequiresPermissions("permission:view")//权限管理;
    @RequestMapping(value = "/oauth2/findOne", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<PermissionDTO> findOne(@RequestParam("id")Integer id)  throws BizException {

        return feignPermissionService.findOne(id);
    }

    @ApiOperation(value="获取权限树的根节点列表", notes="获取权限树的根节点列表")
    @Operate(value = "获取权限树的根节点列表")
    @CrossOrigin
    @RequiresPermissions("permission:view")//权限管理;
    @RequestMapping(value = "/oauth2/findRootList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<PermissionDTO>> findRootList()  throws BizException {
        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        return feignPermissionService.findRootList(shiroAdmin.getId());
    }

    @ApiOperation(value="根据父权限获取子权限列表", notes="根据父权限获取子权限列表")
    @Operate(value = "根据父权限获取子权限列表")
    @CrossOrigin
    @RequiresPermissions("permission:view")//权限管理;
    @RequestMapping(value = "/oauth2/findListByParentId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<PermissionDTO>> findListByParentId(@RequestParam("permissionId")Integer permissionId)  throws BizException {
        return feignPermissionService.findListByParentId(permissionId);
    }


}
