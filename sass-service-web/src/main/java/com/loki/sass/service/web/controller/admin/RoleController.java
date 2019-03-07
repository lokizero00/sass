package com.loki.sass.service.web.controller.admin;

import com.loki.sass.common.code.RoleResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.feignclient.feignService.FeignRoleService;
import com.loki.sass.service.web.aop.bind.Function;
import com.loki.sass.service.web.aop.bind.Operate;
import com.loki.sass.service.web.security.realm.ShiroAdmin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/role")
@Function(value ="角色管理",moduleName = "1登录管理",subModuleName = "")
public class RoleController {

    @Autowired
    private FeignRoleService feignRoleService;

    @Operate(value = "查看角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/selectByUserId", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> selectByUserId(@RequestParam("adminId") Integer adminId)throws BizException{
        return feignRoleService.selectByUserId(adminId);
    }

    @Operate(value = "添加角色")
    @CrossOrigin
    @RequiresPermissions("role:add")//权限管理;
    @RequestMapping(value = "/oauth2/addRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addRole(@Valid @RequestBody RoleRequestVO roleRequestVO, BindingResult bindingResult)throws BizException {
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

    @Operate(value = "删除角色")
    @CrossOrigin
    @RequiresPermissions("role:delete")//权限管理;
    @RequestMapping(value = "/oauth2/deleteRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> deleteRole(@RequestParam("id") Integer id)throws BizException{

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        return feignRoleService.deleteRole(id,shiroAdmin.getId());
    }

    @Operate(value = "编辑角色")
    @CrossOrigin
    @RequiresPermissions("role:edit")//权限管理;
    @RequestMapping(value = "/oauth2/editRole", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> editRole(@Valid @RequestBody RoleRequestVO roleRequestVO, BindingResult bindingResult)throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("编辑失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        roleRequestVO.setUpdateBy(shiroAdmin.getId());

        return feignRoleService.editRole(JsonUtils.objectToJson(roleRequestVO));
    }

    @Operate(value = "通过id查看单个角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/findOne", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException{
        return feignRoleService.findOne(id);
    }

    @Operate(value = "查看所有角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/findAll", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> findAll()throws BizException{
        return feignRoleService.findAll();
    }

    @Operate(value = "分页查看角色")
    @CrossOrigin
    @RequiresPermissions("role:view")//权限管理;
    @RequestMapping(value = "/oauth2/findByPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> findByPage(@Valid @RequestBody RoleRequestVO roleRequestVO, BindingResult bindingResult)throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        return feignRoleService.findByPage(JsonUtils.objectToJson(roleRequestVO));
    }

}
