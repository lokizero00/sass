package com.loki.sass.service.web.controller.admin;

import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.AdminQueryVO;
import com.loki.sass.common.vo.AdminRequestVO;
import com.loki.sass.feignclient.feignService.FeignAdminService;
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

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Function(value ="admin1登录管理",moduleName = "1登录管理",subModuleName = "")
public class AdminController {

    @Autowired
    private FeignAdminService feignAdminService;

    @Operate(value = "添加管理员")
    @CrossOrigin
    @RequiresPermissions("admin:add")//权限管理;
    @RequestMapping(value = "/oauth2/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> addAdmin(@Valid @RequestBody AdminRequestVO adminRequestVO, BindingResult bindingResult)  throws BizException {
        if(bindingResult.hasErrors()){
            String message = String.format("添加失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }
        if (StringUtils.isEmpty(adminRequestVO.getUserName())){
            throw new BizException(AdminResultCode.ADMIN_USERNAME_EMPTY);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        adminRequestVO.setCreateBy(shiroAdmin.getId());

        return feignAdminService.addAdmin(JsonUtils.objectToJson(adminRequestVO));
    }

    @Operate(value = "删除管理员")
    @CrossOrigin
    @RequiresPermissions("admin:delete")//权限管理;
    @RequestMapping(value = "/oauth2/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> deleteAdmin(@RequestParam("id")Integer id) throws BizException{

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        return feignAdminService.deleteAdmin(id,shiroAdmin.getId());
    }

    @Operate(value = "编辑管理员")
    @CrossOrigin
    @RequiresPermissions("admin:edit")//权限管理;
    @RequestMapping(value = "/oauth2/edit", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> editAdmin(@Valid @RequestBody AdminRequestVO adminRequestVO, BindingResult bindingResult) throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("编辑失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        adminRequestVO.setUpdateBy(shiroAdmin.getId());

        return feignAdminService.editAdmin(JsonUtils.objectToJson(adminRequestVO));
    }

    @Operate(value = "通过手机号查询管理员")
    @CrossOrigin
    @RequiresPermissions("admin:view")//权限管理;
    @RequestMapping(value = "/oauth2/selectByMobile", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> selectByMobile(@RequestParam("mobile")String mobile) throws BizException{

        return feignAdminService.selectByMobile(mobile);
    }

    @Operate(value = "通过主键id查询管理员")
    @CrossOrigin
    @RequiresPermissions("admin:view")//权限管理;
    @RequestMapping(value = "/oauth2/findOne", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> findOne(@RequestParam("id") Integer id) throws BizException{

        return feignAdminService.findOne(id);
    }

    @Operate(value = "查询所有管理员")
    @CrossOrigin
    @RequiresPermissions("admin:view")//权限管理;
    @RequestMapping(value = "/oauth2/findAll", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> findAll() throws BizException{

        return feignAdminService.findAll();
    }

    @Operate(value = "分页查询所有管理员")
    @CrossOrigin
    @RequiresPermissions("admin:view")//权限管理;
    @RequestMapping(value = "/oauth2/findByPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<?> findByPage(@Valid @RequestBody AdminQueryVO adminQueryVO, BindingResult bindingResult) throws BizException{
        if(bindingResult.hasErrors()){
            String message = String.format("查询失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
            throw new BizException(message);
        }

        return feignAdminService.findByPage(JsonUtils.objectToJson(adminQueryVO));
    }



}
