package com.loki.sass.api.web.controller.user;

import com.github.pagehelper.PageInfo;
import com.loki.sass.api.web.aop.bind.Function;
import com.loki.sass.api.web.aop.bind.Operate;
import com.loki.sass.api.web.security.realm.ShiroAdmin;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserDetailDTO;
import com.loki.sass.common.dto.UserDoorDTO;
import com.loki.sass.common.dto.UserRegionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.UserDetailQueryVO;
import com.loki.sass.common.vo.UserDoorQueryVO;
import com.loki.sass.common.vo.UserRegionQueryVO;
import com.loki.sass.feignclient.feignService.FeignUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Function(value ="用户相关",moduleName = "用户信息",subModuleName = "")
@Api(tags="用户管理")
public class UserController {

    @Autowired
    private FeignUserService feignUserService;

    @ApiOperation(value="用户详情查询", notes="用户详情查询,用户信息及真实姓名")
    @Operate(value = "记录查询")
    @CrossOrigin
    @RequiresPermissions("user:view")//权限管理;
    @RequestMapping(value = "/oauth2/findUserDetailByPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<PageInfo<UserDetailDTO>> findUserDetailByPage(@RequestBody UserDetailQueryVO userDetailQueryVO)  throws BizException {
        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        userDetailQueryVO.setAdminId(shiroAdmin.getId());

        return feignUserService.findUserDetailByPage(JsonUtils.objectToJson(userDetailQueryVO));
    }

    @ApiOperation(value="用户门禁查询", notes="用户关联门禁查询")
    @Operate(value = "记录查询")
    @CrossOrigin
    @RequiresPermissions("user:view")//权限管理;
    @RequestMapping(value = "/oauth2/findUserDoorByPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<PageInfo<UserDoorDTO>> findUserDoorByPage(@RequestBody UserDoorQueryVO userDoorQueryVO)  throws BizException {
        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        userDoorQueryVO.setAdminId(shiroAdmin.getId());

        return feignUserService.findUserDoorByPage(JsonUtils.objectToJson(userDoorQueryVO));
    }

    @ApiOperation(value="用户区域查询", notes="用户关联区域查询")
    @Operate(value = "记录查询")
    @CrossOrigin
    @RequiresPermissions("user:view")//权限管理;
    @RequestMapping(value = "/oauth2/findUserRegionByPage", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResultDTO<PageInfo<UserRegionDTO>> findUserRegionByPage(@RequestBody UserRegionQueryVO userRegionQueryVO)  throws BizException {
        ShiroAdmin shiroAdmin=(ShiroAdmin) SecurityUtils.getSubject().getPrincipal();
        userRegionQueryVO.setAdminId(shiroAdmin.getId());

        return feignUserService.findUserRegionByPage(JsonUtils.objectToJson(userRegionQueryVO));
    }
}
