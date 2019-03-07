package com.loki.sass.service.manager.service;

import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.service.manager.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void toJson(){
        /*
        {"id":null,"zoneId":0,"role":"角色","description":"角色描述"}
        * */
        RoleRequestVO roleVO = new RoleRequestVO();
        roleVO.setZoneId(0);
        roleVO.setRole("角色");
        roleVO.setDescription("角色描述");
        String objectToJson = JsonUtils.objectToJson(roleVO);
        System.out.println(objectToJson);
    }

    @Test
    public void insert() {
        RoleRequestVO roleVO = new RoleRequestVO();
        roleVO.setZoneId(0);
        roleVO.setRole("角色");
        roleVO.setDescription("角色描述");
        roleService.insert(roleVO);
    }
}