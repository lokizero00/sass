package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.domain.model.Role;
import com.loki.sass.service.manager.api.RoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "v1/selectByUserId",method = RequestMethod.POST)
    List<RoleDTO> selectByUserId(@RequestParam Integer adminId) {
        return roleService.selectByUserId(adminId);
    }
}
