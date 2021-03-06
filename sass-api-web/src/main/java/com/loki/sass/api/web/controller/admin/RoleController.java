package com.loki.sass.api.web.controller.admin;

import com.github.pagehelper.PageInfo;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RolePermissionRequestVO;
import com.loki.sass.common.vo.RoleQueryVO;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
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
@RequestMapping("/role")
@Function(value ="角色管理",moduleName = "1登录管理",subModuleName = "")
@Api(tags="角色管理")
public class RoleController {

    @Autowired
    private FeignRoleService feignRoleService;

    @ApiOperation(value="查看角色", notes="查看角色")
    @Operate(value = "查看角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/selectByUserId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<RoleDTO>> selectByUserId(@RequestParam("adminId") Integer adminId)throws BizException{
        return feignRoleService.selectByUserId(adminId);
    }

    @ApiOperation(value="添加角色", notes="添加角色")
    @Operate(value = "添加角色")
    @CrossOrigin
    @RequiresPermissions("role:add")//权限管理;
    @RequestMapping(value = "/oauth2/addRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> addRole(@Valid @RequestBody RoleRequestVO roleRequestVO, BindingResult bindingResult)throws BizException {
        if(bindingResult.hasErrors()){
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }
        if (StringUtils.isEmpty(roleRequestVO.getRole())){
            throw new BizException(RoleResultCode.ROLE_NAME_EMPTY);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        roleRequestVO.setCreateBy(shiroAdmin.getId());

        return feignRoleService.addRole(JsonUtils.objectToJson(roleRequestVO));
    }

    @ApiOperation(value="删除角色", notes="删除角色")
    @Operate(value = "删除角色")
    @CrossOrigin
    @RequiresPermissions("role:delete")//权限管理;
    @RequestMapping(value = "/oauth2/deleteRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> deleteRole(@RequestParam("id") Integer id)throws BizException{

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        return feignRoleService.deleteRole(id,shiroAdmin.getId());
    }

    @ApiOperation(value="编辑角色", notes="编辑角色")
    @Operate(value = "编辑角色")
    @CrossOrigin
    @RequiresPermissions("role:edit")//权限管理;
    @RequestMapping(value = "/oauth2/editRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> editRole(@Valid @RequestBody RoleRequestVO roleRequestVO, BindingResult bindingResult)throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("编辑失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        roleRequestVO.setUpdateBy(shiroAdmin.getId());

        return feignRoleService.editRole(JsonUtils.objectToJson(roleRequestVO));
    }

    @ApiOperation(value="通过id查看单个角色", notes="通过id查看单个角色")
    @Operate(value = "通过id查看单个角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/findOne", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<RoleDTO> findOne(@RequestParam("id") Integer id)throws BizException{
        return feignRoleService.findOne(id);
    }

    @ApiOperation(value="查看所有角色", notes="查看所有角色")
    @Operate(value = "查看所有角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/findAll", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<RoleDTO>> findAll()throws BizException{
        return feignRoleService.findAll();
    }

    @ApiOperation(value="分页查看角色", notes="分页查看角色，adminId默认写0")
    @Operate(value = "分页查看角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/findByPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<PageInfo<RoleDTO>> findByPage(@Valid @RequestBody RoleQueryVO roleQueryVO, BindingResult bindingResult)throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        roleQueryVO.setAdminId(shiroAdmin.getId());

        return feignRoleService.findByPage(JsonUtils.objectToJson(roleQueryVO));
    }

    @ApiOperation(value="查看角色拥有的权限", notes="查看角色拥有的权限")
    @Operate(value = "查看角色拥有的权限")
    @CrossOrigin
    @RequiresPermissions("role:auth")//权限管理;
    @RequestMapping(value = "/oauth2/findOwnPermissions", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<List<PermissionDTO>> findOwnPermissions(@RequestParam("roleId")Integer roleId)throws BizException{
        return feignRoleService.findOwnPermissions(roleId);
    }

    @ApiOperation(value="更新角色拥有的权限", notes="更新角色拥有的权限")
    @Operate(value = "更新角色拥有的权限")
    @CrossOrigin
    @RequiresPermissions("role:auth")//权限管理;
    @RequestMapping(value = "/oauth2/updateOwnPermissions", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<Boolean> updateOwnPermissions(@Valid RolePermissionRequestVO rolePermissionRequestVO,BindingResult bindingResult)throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("更新失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }
        return feignRoleService.updateOwnPermissions(JsonUtils.objectToJson(rolePermissionRequestVO));
    }


}
