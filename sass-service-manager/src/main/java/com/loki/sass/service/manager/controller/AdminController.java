package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.AdminLoginRequestVO;
import com.loki.sass.common.vo.AdminVO;
import com.loki.sass.domain.model.Admin;
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

    @RequestMapping(value = "v1/selectByMobile",method = RequestMethod.GET)
    public AdminDTO selectByMobile(@RequestParam String mobile) {

        return adminService.selectByMobile(mobile);
    }

    @RequestMapping(value= "v1/insert", method = RequestMethod.POST)
    public Integer insert(AdminVO adminVO){
        return adminService.insert(adminVO);
    }

    @RequestMapping(value="v1/update",method = RequestMethod.POST)
    public Integer update(AdminVO adminVO){
        return adminService.update(adminVO);
    }

    @RequestMapping(value="v1/deleteById",method = RequestMethod.GET)
    public void delete(@RequestParam Integer id){

        adminService.deleteById(id);
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.GET)
    public List<AdminDTO> findAll(){
        return adminService.findAll();
    }


    @RequestMapping(value="v1/findByPage/{current}/{count}",method = RequestMethod.GET)
    public List<AdminDTO> findByPage(@PathVariable("current")Integer current,@PathVariable("count")Integer count){
        return adminService.findByPage(current,count);
    }

}
