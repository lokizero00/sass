package com.loki.sass.service.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.AdminResultCode;
import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.vo.AdminQueryVO;
import com.loki.sass.common.vo.AdminRequestVO;
import com.loki.sass.domain.mapper.AdminMapper;
import com.loki.sass.domain.model.Admin;
import com.loki.sass.domain.model.AdminExample;
import com.loki.sass.domain.po.AdminPO;
import com.loki.sass.service.manager.api.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Service
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminDTO selectByMobile(String mobile)throws BizException {
        if(StringUtils.isEmpty(mobile)){
            throw new BizException(AdminResultCode.ADMIN_MOBILE_WRONG);
        }
        Admin admin = adminMapper.selectByMobile(mobile);
        AdminDTO adminDTO = ConvertUtils.sourceToTarget(admin,AdminDTO.class);
        return adminDTO;
    }

    @Override
    public AdminDTO findOne(Integer id)throws BizException{
        Admin admin = adminMapper.selectByPrimaryKey(id);
        AdminDTO adminDTO = ConvertUtils.sourceToTarget(admin,AdminDTO.class);
        return adminDTO;
    }

    @Override
    public List<AdminDTO> findAll() throws BizException{
        AdminExample adminExample = new AdminExample();

        List<Admin> adminList = adminMapper.selectByExample(new AdminExample());
        List<AdminDTO> adminDTOList = ConvertUtils.sourceToTarget(adminList, AdminDTO.class);
        return adminDTOList;
    }

    @Override
    public PageInfo<AdminDTO> getAdminListSearch(AdminQueryVO adminQueryVO) throws BizException{
        if (!StringUtils.isEmpty(adminQueryVO.getPage()) && !StringUtils.isEmpty(adminQueryVO.getRows())) {
            PageHelper.startPage(adminQueryVO.getPage(), adminQueryVO.getRows());
        }
        List<AdminPO> list = adminMapper.selectByParam(adminQueryVO.getUsername(),adminQueryVO.getCreateByName(),adminQueryVO.getUpdateByName(),adminQueryVO.getState());
        List<AdminDTO> dtoList= ConvertUtils.sourceToTarget(list,AdminDTO.class);
        PageInfo<AdminDTO> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    @Override
    public boolean insert(AdminRequestVO adminRequestVO) throws BizException{
        if(adminRequestVO==null){
            throw new BizException(AdminResultCode.ADMIN_IS_NULL);
        }
        Admin admin = ConvertUtils.sourceToTarget(adminRequestVO,Admin.class);
        String username = admin.getUserName();
        if(StringUtils.isEmpty(username)){
            throw new BizException(AdminResultCode.ADMIN_USERNAME_EMPTY);
        }
        Integer count = adminMapper.checkName(username);
        if(count>0){
            throw new BizException(AdminResultCode.ADMIN_USERNAME_EXIST);
        }
        Integer result = 0;
        try{
            String salt = BCrypt.gensalt();
            String password = adminRequestVO.getPassword();
            admin.setPassword(BCrypt.hashpw(password , salt));
            admin.setSalt(salt);
            admin.setIsSuper(0);//默认不是超级管理员
            admin.setState(1);//默认是启用状态
            admin.setIsDeleted(0);//默认未被删除

            result = adminMapper.insert(admin);
        }catch(Exception e){
            log.info("[adminService插入记录],存在异常:{}",e);
            throw new BizException(AdminResultCode.ADMIN_ADD_ERROR);
        }
        return result>0;
    }

    @Override
    public boolean deleteById(Integer id,Integer operatorId)throws BizException {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if(admin==null){
            log.info("[adminService删除记录],待删除记录找不到,传参id:{}",id);
            throw new BizException(AdminResultCode.ADMIN_IS_NULL);
        }
        Admin operatorAdmin = adminMapper.selectByPrimaryKey(operatorId);
        if(operatorAdmin==null){
            log.info("[adminService删除记录],操作者记录找不到,operatorId:{}",operatorId);
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int result = 0;
        try{
            admin.setIsDeleted(1);
            admin.setUpdateBy(operatorId);
            result = adminMapper.updateByPrimaryKeySelective(admin);
        }catch(Exception e){
            throw new BizException(AdminResultCode.ADMIN_DELETE_ERROR);
        }
        return result>0;
    }

    @Override
    public boolean update(AdminRequestVO adminRequestVO) throws BizException{
        Admin checkExist = adminMapper.selectByPrimaryKey(adminRequestVO.getId());
        if(checkExist==null){
            throw new BizException(AdminResultCode.ADMIN_IS_NULL);
        }
        Admin operatorAdmin = adminMapper.selectByPrimaryKey(adminRequestVO.getUpdateBy());
        if(operatorAdmin==null){
            log.info("[adminService删除记录],操作者记录找不到,operatorId:{}",adminRequestVO.getUpdateBy());
            throw new BizException(AdminResultCode.ADMIN_OPERATOR_NOT_EXIST);
        }
        int result = 0;
        try {
            Admin admin = ConvertUtils.sourceToTarget(adminRequestVO, Admin.class);
            //重新生成盐值和密码
            String salt = BCrypt.gensalt();
            String password = adminRequestVO.getPassword();
            admin.setPassword(BCrypt.hashpw(password , salt));
            admin.setSalt(salt);
            result = adminMapper.updateByPrimaryKeySelective(admin);
        }catch(Exception e){
            log.error("[adminService更新记录],出现异常,e={}",e);
            throw new BizException(AdminResultCode.ADMIN_UPDATE_ERROR);
        }
        return result>0;
    }


}
