package com.loki.sass.service.manager.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.AdminQueryVO;
import com.loki.sass.common.vo.AdminRequestVO;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
public interface AdminService {

    AdminDTO selectByMobile(String mobile)throws BizException;

    boolean insert(AdminRequestVO adminRequestVO)throws BizException;

    boolean deleteById(Integer id,Integer operatorId)throws BizException;

    boolean update(AdminRequestVO adminRequestVO)throws BizException;

    AdminDTO findOne(Integer id)throws BizException;

    List<AdminDTO> findAll()throws BizException;

    PageInfo<AdminDTO> getAdminListSearch(AdminQueryVO adminQueryVO)throws BizException;
}
