package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.PermissionDTO;
import com.loki.sass.domain.model.Permission;
import com.loki.sass.service.manager.api.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping(value = "v1/selectByRoleId",method = RequestMethod.POST)
    List<PermissionDTO> selectByRoleId(@RequestParam Integer roleId) {
        return permissionService.selectByRoleId(roleId);
    }
}
