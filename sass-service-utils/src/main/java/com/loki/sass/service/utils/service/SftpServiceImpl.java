package com.loki.sass.service.utils.service;

import com.loki.sass.service.utils.api.SftpService;
import com.loki.sass.service.utils.config.SftpConfig;
import com.loki.sass.service.utils.util.sftp.SFTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * created by lokizero00 on 2019-02-16
 */
@Slf4j
@Service
public class SftpServiceImpl implements SftpService {

    @Override
    public boolean uploadAuthImageServer(InputStream is, String serverFileName) {
        SFTPUtil sftp = new SFTPUtil(SftpConfig.getInstance().getSftpAuthImageUsername(), SftpConfig.getInstance().getSftpAuthImagePassword(), SftpConfig.getInstance().getSftpAuthImageHost(), SftpConfig.getInstance().getSftpPort());
        try {
            sftp.login();
            sftp.upload(SftpConfig.getInstance().getSftpAuthImageServerPath(), serverFileName, is);
            sftp.logout();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
