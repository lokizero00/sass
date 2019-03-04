package com.loki.sass.zuul.api;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * created by lokizero00 on 2019-02-14
 */
public interface PermissionService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
