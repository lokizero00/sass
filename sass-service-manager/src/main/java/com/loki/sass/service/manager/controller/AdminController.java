package com.loki.sass.service.manager.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.AdminQueryVO;
import com.loki.sass.common.vo.AdminRequestVO;
import com.loki.sass.service.manager.api.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-17
 */

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "v1/selectByMobile",method = RequestMethod.POST)
    public ResultDTO<AdminDTO> selectByMobile(@RequestParam("mobile") String mobile)throws BizException {
        ResultDTO<AdminDTO> resultDTO = new ResultDTO();
        resultDTO.setSuccess(false);
        resultDTO.setModule(adminService.selectByMobile(mobile));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value= "v1/addAdmin", method = RequestMethod.POST)
    public ResultDTO<?> insert(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException{
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        AdminRequestVO adminRequestVO = JsonUtils.jsonToObject(adminRequestVOJson,AdminRequestVO.class);
        resultDTO.setSuccess(adminService.insert(adminRequestVO));
        return resultDTO;
    }

    @RequestMapping(value="v1/editAdmin",method = RequestMethod.POST)
    public ResultDTO<?> update(@RequestParam("adminRequestVOJson") String adminRequestVOJson)throws BizException{
        ResultDTO<Boolean> resultDTO = new ResultDTO();
        AdminRequestVO adminRequestVO = JsonUtils.jsonToObject(adminRequestVOJson,AdminRequestVO.class);
        resultDTO.setSuccess(adminService.update(adminRequestVO));
        return resultDTO;
    }

    @RequestMapping(value="v1/deleteAdmin",method = RequestMethod.POST)
    public ResultDTO<?> delete(@RequestParam("id") Integer id,@RequestParam("operatorId") Integer operatorId)throws BizException{
        ResultDTO<Boolean> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(adminService.deleteById(id,operatorId));
        return resultDTO;
    }

    @RequestMapping(value="v1/findOne",method = RequestMethod.POST)
    public ResultDTO<?> findOne(@RequestParam("id") Integer id)throws BizException{
        ResultDTO<AdminDTO> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(adminService.findOne(id));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.POST)
    public ResultDTO<?> findAll()throws BizException{
        ResultDTO<List<AdminDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        resultDTO.setModule(adminService.findAll());
        resultDTO.setSuccess(true);
        return resultDTO;
    }


    @RequestMapping(value="v1/findByPage",method = RequestMethod.POST)
    public ResultDTO<?> findByPage(@RequestParam("adminQueryVOJson") String adminQueryVOJson)throws BizException{
        ResultDTO<PageInfo<AdminDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setSuccess(false);
        AdminQueryVO adminQueryVO = JsonUtils.jsonToObject(adminQueryVOJson, AdminQueryVO.class);
        resultDTO.setModule(adminService.getAdminListSearch(adminQueryVO));
        resultDTO.setSuccess(true);
        return resultDTO;
    }

}
