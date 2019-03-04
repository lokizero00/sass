package com.loki.sass.service.utils.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by lokizero00
 * date 2019-02-12
 */
@Data
@Component
public class SftpConfig {

    private static SftpConfig sftpConfig = null;

    private SftpConfig(){}

    private @Value("${sftp.port}") Integer sftpPort;
    private @Value("${sftp.authimage.host}") String sftpAuthImageHost;
    private @Value("${sftp.authimage.username}") String sftpAuthImageUsername;
    private @Value("${sftp.authimage.password}") String sftpAuthImagePassword;
    private @Value("${sftp.authimage.serverpath}") String sftpAuthImageServerPath;

    @Bean
    public static SftpConfig getInstance() {
        if(sftpConfig == null)
            sftpConfig = new SftpConfig();
        return sftpConfig;
    }
}
