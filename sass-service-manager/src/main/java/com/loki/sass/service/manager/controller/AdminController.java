package com.loki.sass.service.manager.controller;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.service.manager.api.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
