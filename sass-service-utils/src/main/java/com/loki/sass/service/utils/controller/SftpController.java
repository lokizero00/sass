package com.loki.sass.service.utils.controller;

import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.service.utils.api.SftpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

/**
 * created by lokizero00 on 2019-02-16
 */
@Slf4j
@RestController
@RequestMapping("/sftp")
public class SftpController {
    @Autowired
    SftpService sftpService;

    @RequestMapping(value = "v1/uploadAuthImageServer",method = RequestMethod.POST)
    public ResultDTO<Boolean> uploadAuthImageServer(@RequestParam InputStream is, @RequestParam String serverFileName){
        ResultDTO<Boolean> result=new ResultDTO<>();
        result.setSuccess(sftpService.uploadAuthImageServer(is,serverFileName));
        return result;
    }
}
