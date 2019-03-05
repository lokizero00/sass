package com.loki.sass.service.manager.service;

import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.AdminVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Test
    public void toJson(){
        /*
        {"id":7,"userName":"张七","realName":"张七真实姓名","avatarUrl":"www.baidu.com","mobile":"15154123657","password":"123127"}
        * */
        AdminVO adminVO = new AdminVO();
        adminVO.setId(7);
        adminVO.setUserName("张七");
        adminVO.setPassword("123127");
        adminVO.setRealName("张七真实姓名");
        adminVO.setMobile("15154123657");
        adminVO.setAvatarUrl("www.baidu.com");
        String objectToJson = JsonUtils.objectToJson(adminVO);
        System.out.println(objectToJson);
    }

}