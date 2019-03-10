package com.loki.sass.service.resident.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserDetailDTO;
import com.loki.sass.common.dto.UserDoorDTO;
import com.loki.sass.common.dto.UserRegionDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.util.ResultDTOUtils;
import com.loki.sass.common.vo.UserDetailQueryVO;
import com.loki.sass.common.vo.UserDoorQueryVO;
import com.loki.sass.common.vo.UserRegionQueryVO;
import com.loki.sass.service.resident.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/v1/findUserDetailByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<PageInfo<UserDetailDTO>> findUserDetailByPage(@RequestParam("userDetailQueryVOJson") String userDetailQueryVOJson)  throws BizException {
        UserDetailQueryVO userDetailQueryVO = JsonUtils.jsonToObject(userDetailQueryVOJson, UserDetailQueryVO.class);
        return ResultDTOUtils.success(userService.findUserDetailByPage(userDetailQueryVO));
    }

    @RequestMapping(value = "/v1/findUserDoorByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<PageInfo<UserDoorDTO>> findUserDoorByPage(@RequestParam("userDoorQueryVOJson") String userDoorQueryVOJson)  throws BizException {
        UserDoorQueryVO userDoorQueryVO = JsonUtils.jsonToObject(userDoorQueryVOJson, UserDoorQueryVO.class);
        return ResultDTOUtils.success(userService.findUserDoorByPage(userDoorQueryVO));
    }

    @RequestMapping(value = "/v1/findUserRegionByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<PageInfo<UserRegionDTO>> findUserRegionByPage(@RequestParam("userRegionQueryVOJson") String userRegionQueryVOJson)  throws BizException {
        UserRegionQueryVO userRegionQueryVO = JsonUtils.jsonToObject(userRegionQueryVOJson, UserRegionQueryVO.class);
        return ResultDTOUtils.success(userService.findUserRegionByPage(userRegionQueryVO));
    }

}
