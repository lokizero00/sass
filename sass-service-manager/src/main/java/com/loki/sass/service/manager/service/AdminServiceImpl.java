package com.loki.sass.service.manager.service;

import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.AdminVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.AdminExample;
import com.loki.sass.service.manager.api.AdminService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public AdminDTO selectByMobile(String mobile) {
        Admin admin = adminMapper.selectByMobile(mobile);
        AdminDTO adminDTO = ConvertUtils.sourceToTarget(admin,AdminDTO.class);
        return adminDTO;
    }

    @Override
    public AdminDTO selectById(Integer id){
        Admin admin = adminMapper.selectByPrimaryKey(id);
        AdminDTO adminDTO = ConvertUtils.sourceToTarget(admin,AdminDTO.class);
        return adminDTO;
    }

    @Override
    public List<AdminDTO> findAll() {
        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        List<AdminDTO> adminDTOList = ConvertUtils.sourceToTarget(adminList, AdminDTO.class);
        return adminDTOList;
    }

    @Override
    public List<AdminDTO> findByPage(Integer current, Integer count) {
        if(current==null){
            current = 1;
        }
        if(count==null){
            count = 10;
        }
        AdminExample adminExample = new AdminExample();
        adminExample.setOffset((current-1)*count);
        adminExample.setLimit(count);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        List<AdminDTO> rtnList = ConvertUtils.sourceToTarget(adminList, AdminDTO.class);
        return rtnList;
    }

    @Override
    public Integer insert(AdminVO adminVO) {
        Admin admin = ConvertUtils.sourceToTarget(adminVO,Admin.class);
        //TODO admin非页面参数的默认赋值过程

        String salt = BCrypt.gensalt();
        String password = adminVO.getPassword();
        admin.setPassword(BCrypt.hashpw(password , salt));
        admin.setSalt(salt);
        admin.setIsSuper(0);//默认不是超级管理员
        admin.setState(1);//默认是启用状态
        admin.setIsDeleted(0);//默认未被删除

        int result = adminMapper.insert(admin);
        logger.info("[adminService插入记录],admin={},插入结果{}",admin,result);
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        logger.info("[adminService删除记录],待删除记录id:{}",id);
        if(id==null){
            throw new NullPointerException("id不能为空");
        }
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(AdminVO adminVO) {
        if(adminVO==null || adminVO.getId()==null){
            throw new NullPointerException("主键id不能为空");
        }
        Admin admin = ConvertUtils.sourceToTarget(adminVO, Admin.class);

        //重新生成盐值和密码
        String salt = BCrypt.gensalt();
        String password = adminVO.getPassword();
        admin.setPassword(BCrypt.hashpw(password , salt));
        admin.setSalt(salt);

        int result = adminMapper.updateByPrimaryKeySelective(admin);
        logger.info("[adminService更新记录],参数:{},处理结果result={}",adminVO,result);
        return result;
    }


}
