package com.loki.sass.common;

import com.loki.sass.common.util.HttpRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class SassCommonApplicationTests {

//    @Test
//    public void contextLoads() {
//    }

    @Test
    public void postTest(){
//        //发送 GET 请求
//        String s= HttpRequest.sendGet("http://localhost:6144/API/v1/user/check_log.php", "key=123&v=456");
//        System.out.println(s);

//        //发送 POST 请求
//        String sr=HttpRequest.sendPost("https://power.btiot.com.cn/API/v1/user/check_log.php", "name=0577admin&pwd=123456");
//        System.out.println(sr);

//        //发送 POST 请求
//        String sr1=HttpRequest.sendPost("https://power.btiot.com.cn/API/v1/user/get_login_info.php", "token=0739189d3ec811673a365f8e4a7759c8d039ae59");
//        System.out.println(sr1);

        //发送 POST 请求
        String sr1=HttpRequest.sendPost("https://power.btiot.com.cn/API/v1/electrical/detector/get_list.php", "token=0739189d3ec811673a365f8e4a7759c8d039ae59");
        System.out.println(sr1);
    }

}

