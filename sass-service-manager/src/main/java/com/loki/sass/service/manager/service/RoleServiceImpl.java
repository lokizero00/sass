package com.loki.sass.service.manager.service;

import com.loki.sass.common.dto.RoleDTO;
import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RoleVO;
import com.loki.sass.domain.mapper.RoleMapper;
import com.loki.sass.domain.model.Role;
import com.loki.sass.domain.model.RoleExample;
import com.loki.sass.service.manager.api.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-21
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public List<RoleDTO> selectByUserId(Integer adminId) {
        List<Role> roleList=roleMapper.selectByAdminId(adminId);
        List<RoleDTO> roleDTOList= ConvertUtils.sourceToTarget(roleList,RoleDTO.class);
        return roleDTOList;
    }

    @Override
    public Integer insert(RoleVO roleVO) {
        Role role = ConvertUtils.sourceToTarget(roleVO, Role.class);
        role.setAvailable(1);
        role.setCreateBy(0);
        role.setIsDeleted(0);
        return roleMapper.insert(role);
    }

    @Override
    public Integer update(RoleVO roleVO) {
        if(roleVO==null || roleVO.getId()==null){
            throw new NullPointerException("角色id为空");
        }
        Role role = ConvertUtils.sourceToTarget(roleVO, Role.class);
        int result = roleMapper.updateByPrimaryKeySelective(role);
        logger.info("[roleService更新记录],roleVO={},result={}",roleVO,result);
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        int result = roleMapper.deleteByPrimaryKey(id);
        logger.info("[roleService删除记录],id={}",id);
        return result;
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roleList = roleMapper.selectByExample(new RoleExample());
        List<RoleDTO> roleDTOList = ConvertUtils.sourceToTarget(roleList, RoleDTO.class);
        return roleDTOList;
    }

    @Override
    public List<RoleDTO> findByPage(Integer current, Integer count) {
        if(current==null){
            current=1;
        }
        if(count==null){
            count = 10;
        }
        RoleExample roleExample = new RoleExample();
        roleExample.setOffset((current-1)*count);
        roleExample.setLimit(count);
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        List<RoleDTO> roleDTOList = ConvertUtils.sourceToTarget(roleList, RoleDTO.class);
        return roleDTOList;
    }

    @Override
    public RoleDTO findById(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        RoleDTO roleDTO = ConvertUtils.sourceToTarget(role, RoleDTO.class);
        return roleDTO;
    }


}
