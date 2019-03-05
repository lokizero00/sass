package com.loki.sass.service.manager.api;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.vo.AdminVO;
import com.loki.sass.domain.model.Admin;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface AdminService {

    public AdminDTO selectByMobile(String mobile);

    public Integer insert(AdminVO adminVO);

    public Integer deleteById(Integer id);

    public Integer update(AdminVO adminVO);

    public AdminDTO selectById(Integer id);

    public List<AdminDTO> findAll();

    public List<AdminDTO> findByPage(Integer current,Integer count);
}
