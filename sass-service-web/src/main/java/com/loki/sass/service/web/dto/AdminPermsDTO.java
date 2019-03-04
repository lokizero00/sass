package com.loki.sass.service.web.dto;

import lombok.Data;

import java.util.Set;

/**
 * created by lokizero00 on 2019-02-21
 */
@Data
public class AdminPermsDTO  {

    private Set<String> roleSet;
    private Set<String> permissions;
}