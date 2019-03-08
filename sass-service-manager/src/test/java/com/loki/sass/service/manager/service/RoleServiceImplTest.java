package com.loki.sass.service.manager.service;

import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RolePermissionRequestVO;
import com.loki.sass.common.vo.RoleRequestVO;
import com.loki.sass.service.manager.api.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void toJson(){
        RolePermissionRequestVO rolePermissionRequestVO = new RolePermissionRequestVO();
        rolePermissionRequestVO.setRoleId(15);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(6);
        rolePermissionRequestVO.setPermissionIdsList(list);
        String s = JsonUtils.objectToJson(rolePermissionRequestVO);
        System.out.println(s);
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