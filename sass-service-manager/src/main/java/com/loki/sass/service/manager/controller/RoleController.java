package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RoleVO;
import com.loki.sass.domain.model.Role;
import com.loki.sass.service.manager.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-17
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "v1/selectByUserId",method = RequestMethod.POST)
    List<RoleDTO> selectByUserId(@RequestParam Integer adminId) {

        return roleService.selectByUserId(adminId);
    }

    @RequestMapping(value = "v1/insert",method = RequestMethod.POST)
    public Integer insert(@RequestParam String roleVOJson){
        RoleVO roleVO = JsonUtils.jsonToObject(roleVOJson, RoleVO.class);
        return roleService.insert(roleVO);
    }

    @RequestMapping(value = "v1/deleteById",method = RequestMethod.POST)
    public Integer deleteById(@RequestParam Integer id){
        return roleService.deleteById(id);
    }

    @RequestMapping(value = "v1/update",method = RequestMethod.POST)
    public Integer updateById(@RequestParam String roleVOJson){
        RoleVO roleVO = JsonUtils.jsonToObject(roleVOJson,RoleVO.class);
        return roleService.update(roleVO);
    }

    @RequestMapping(value = "v1/findById",method = RequestMethod.POST)
    public RoleDTO findById(@RequestParam Integer id){
        return roleService.findById(id);
    }

    @RequestMapping(value = "v1/findAll",method = RequestMethod.POST)
    public List<RoleDTO> findAll(){
        return roleService.findAll();
    }

    @RequestMapping(value = "v1/findByPage/{current}/{size}",method = RequestMethod.POST)
    public List<RoleDTO> findByPage(@PathVariable("current")Integer current,@PathVariable("size") Integer size){
        return roleService.findByPage(current,size);
    }
}
