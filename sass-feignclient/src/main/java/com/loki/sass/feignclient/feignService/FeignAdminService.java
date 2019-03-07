package com.loki.sass.feignclient.feignService;

import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-manager")
public interface FeignAdminService {
    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/selectByMobile")
    public ResultDTO<?> selectByMobile(@RequestParam("mobile") String mobile)throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/addAdmin")
    public ResultDTO<?> addAdmin(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException;

    @RequestMapping(method = RequestMethod.POST, value = "/admin/v1/editAdmin")
    public ResultDTO<?> editAdmin(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="/admin/v1/deleteAdmin")
    public ResultDTO<?> deleteAdmin(@RequestParam("id") Integer id,@RequestParam("operatorId") Integer operatorId)throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="admin/v1/findOne")
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="admin/v1/findAll")
    public ResultDTO<?> findAll()throws BizException;

    @RequestMapping(method = RequestMethod.POST,value="admin/v1/findByPage")
    public ResultDTO<?> findByPage(@RequestParam("adminQueryVOJson") String adminQueryVOJson)throws BizException;

}
