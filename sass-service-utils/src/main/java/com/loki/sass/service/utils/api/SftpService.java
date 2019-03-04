package com.loki.sass.service.utils.api;

import java.io.InputStream;

/**
 * created by lokizero00 on 2019-02-16
 */
public interface SftpService {
    public boolean uploadAuthImageServer(InputStream is, String serverFileName);
}
