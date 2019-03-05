package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.AdminVO;
import com.loki.sass.service.manager.api.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public AdminDTO selectByMobile(@RequestParam String mobile) {

        return adminService.selectByMobile(mobile);
    }

    @RequestMapping(value= "v1/insert", method = RequestMethod.POST)
    public Integer insert(@RequestParam String adminVOJson){
        AdminVO adminVO = JsonUtils.jsonToObject(adminVOJson,AdminVO.class);
        return adminService.insert(adminVO);
    }

    @RequestMapping(value="v1/update",method = RequestMethod.POST)
    public Integer update(@RequestParam String adminVOJson){
        AdminVO adminVO = JsonUtils.jsonToObject(adminVOJson,AdminVO.class);
        return adminService.update(adminVO);
    }

    @RequestMapping(value="v1/deleteById",method = RequestMethod.POST)
    public Integer delete(@RequestParam Integer id){

        return adminService.deleteById(id);
    }

    @RequestMapping(value="v1/findById",method = RequestMethod.POST)
    public AdminDTO findOne(@RequestParam Integer id){
        return adminService.selectById(id);
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.POST)
    public List<AdminDTO> findAll(){
        return adminService.findAll();
    }


    @RequestMapping(value="v1/findByPage/{current}/{count}",method = RequestMethod.POST)
    public List<AdminDTO> findByPage(@PathVariable(value="current")Integer current,@PathVariable("count")Integer count){
        return adminService.findByPage(current,count);
    }

}
