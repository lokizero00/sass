package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.PermissionVO;
import com.loki.sass.service.manager.api.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-17
 */
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping(value = "v1/selectByRoleId",method = RequestMethod.POST)
    List<PermissionDTO> selectByRoleId(@RequestParam Integer roleId) {
        return permissionService.selectByRoleId(roleId);
    }

    @RequestMapping(value= "v1/insert", method = RequestMethod.POST)
    public Integer insert(String permissionVOJson){
        PermissionVO permissionVO = JsonUtils.jsonToObject(permissionVOJson, PermissionVO.class);
        return permissionService.insert(permissionVO);
    }

    @RequestMapping(value="v1/update",method = RequestMethod.POST)
    public Integer update(String permissionVOJson){
        PermissionVO permissionVO = JsonUtils.jsonToObject(permissionVOJson, PermissionVO.class);
        return permissionService.update(permissionVO);
    }

    @RequestMapping(value="v1/deleteById",method = RequestMethod.POST)
    public void delete(@RequestParam Integer id){

        permissionService.deleteById(id);
    }

    @RequestMapping(value="v1/findById",method = RequestMethod.POST)
    public PermissionDTO findOne(Integer id){
        return permissionService.selectById(id);
    }

    @RequestMapping(value="v1/findAll",method = RequestMethod.POST)
    public List<PermissionDTO> findAll(){

        return permissionService.findAll();
    }

    @RequestMapping(value="v1/findByPage/{current}/{count}",method = RequestMethod.POST)
    public List<PermissionDTO> findByPage(@PathVariable(value="current")Integer current, @PathVariable("count")Integer count){
        return permissionService.findByPage(current,count);
    }
}
