package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.UserDetailDTO;
import com.loki.sass.common.dto.UserDoorDTO;
import com.loki.sass.common.dto.UserRegionDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("sass-service-resident")
public interface FeignUserService {

    @RequestMapping(value = "/user/v1/findUserDetailByPage", method = RequestMethod.POST)
    public ResultDTO<PageInfo<UserDetailDTO>> findUserDetailByPage(@RequestParam("userDetailQueryVOJson") String userDetailQueryVOJson)  throws BizException;

    @RequestMapping(value = "/user/v1/findUserDoorByPage", method = RequestMethod.POST)
    public ResultDTO<PageInfo<UserDoorDTO>> findUserDoorByPage(@RequestParam("userDoorQueryVOJson") String userDoorQueryVOJson)  throws BizException ;

    @RequestMapping(value = "/user/v1/findUserRegionByPage", method = RequestMethod.POST)
    public ResultDTO<PageInfo<UserRegionDTO>> findUserRegionByPage(@RequestParam("userRegionQueryVOJson") String userRegionQueryVOJson)  throws BizException ;
}
