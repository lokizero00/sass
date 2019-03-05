package com.loki.sass.service.manager.service;

import com.loki.sass.common.util.ConvertUtils;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.PermissionVO;
import com.loki.sass.domain.model.Permission;
import com.loki.sass.service.manager.api.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceImplTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void toJson(){
        /*
        {"id":null,"name":"删除管理员","resourceType":"menu","url":"admin/delete","permission":"admin/delete","parentId":0,"isRegional":0}
        * */
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setName("删除管理员");
        permissionVO.setResourceType("menu");
        permissionVO.setUrl("admin/delete");
        permissionVO.setPermission("admin/delete");
        permissionVO.setParentId(0);
        permissionVO.setIsRegional(0);
        String objectToJson = JsonUtils.objectToJson(permissionVO);
        System.out.println(objectToJson);
    }

    @Test
    public void insert() {
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setName("删除管理员");
        permissionVO.setResourceType("menu");
        permissionVO.setUrl("admin/delete");
        permissionVO.setPermission("admin/delete");
        permissionVO.setParentId(0);
        permissionVO.setIsRegional(0);
        permissionService.insert(permissionVO);
    }

    @Test
    public void update(){
        PermissionVO permissionVO = new PermissionVO();
        permissionVO.setId(8);
        permissionVO.setName("批量删除管理员");
        permissionVO.setResourceType("menu");
        permissionVO.setUrl("admin/delete");
        permissionVO.setPermission("admin/delete");
        permissionVO.setParentId(0);
        permissionVO.setIsRegional(0);
        permissionService.update(permissionVO);
    }
}