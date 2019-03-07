package com.loki.sass.service.manager.service;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.AdminDTO;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.AdminQueryVO;
import com.loki.sass.common.vo.AdminRequestVO;
import com.loki.sass.service.manager.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void toJson(){
        /*
        {"id":7,"userName":"张七","realName":"张七真实姓名","avatarUrl":"www.baidu.com","mobile":"15154123657","password":"123127"}
        * */
        AdminRequestVO adminRequestVO = new AdminRequestVO();
        adminRequestVO.setId(7);
        adminRequestVO.setUserName("张七");
        adminRequestVO.setPassword("123127");
        adminRequestVO.setRealName("张七真实姓名");
        adminRequestVO.setMobile("15154123657");
        adminRequestVO.setAvatarUrl("www.baidu.com");
        String objectToJson = JsonUtils.objectToJson(adminRequestVO);
        System.out.println(objectToJson);
    }

    @Test
    public void adminQueryVOToJson(){
        /*
        {"page":2,"rows":2,"username":null,"createByName":null,"updateByName":null,"state":null}
        * */
        AdminQueryVO adminQueryVO = new AdminQueryVO();
        adminQueryVO.setPage(2);
        adminQueryVO.setRows(2);
        String objectToJson = JsonUtils.objectToJson(adminQueryVO);
        System.out.println(objectToJson);
    }

    @Test
    public void getAdminListSearch() {
        AdminQueryVO adminQueryVO = new AdminQueryVO();
        adminQueryVO.setPage(2);
        adminQueryVO.setRows(2);
        PageInfo<AdminDTO> adminDTOPageInfo = adminService.getAdminListSearch(adminQueryVO);
        System.out.println(adminDTOPageInfo);
    }
}