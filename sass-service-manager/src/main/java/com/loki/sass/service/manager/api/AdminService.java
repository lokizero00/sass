package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.AdminDTO;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface AdminService {
    public AdminDTO selectByMobile(String mobile);
}
