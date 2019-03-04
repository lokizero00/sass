package com.loki.sass.service.manager.service;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.service.manager.api.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminDTO selectByMobile(String mobile) {
        Admin admin=adminMapper.selectByMobile(mobile);
        AdminDTO adminDTO= ConvertUtils.sourceToTarget(admin,AdminDTO.class);
        return adminDTO;
    }
}
