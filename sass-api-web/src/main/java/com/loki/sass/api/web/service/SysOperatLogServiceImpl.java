package com.loki.sass.api.web.service;

import com.loki.sass.domain.mapper.SysOperatLogMapper;
import com.loki.sass.domain.model.SysOperatLog;
import com.loki.sass.api.web.api.SysOperatLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by lokizero00 on 2019-02-28
 */
@Slf4j
@Service
@Transactional
public class SysOperatLogServiceImpl implements SysOperatLogService {
    @Autowired
    SysOperatLogMapper sysOperatLogMapper;

    @Override
    public int insertSysOperatLog(SysOperatLog record) {
        return sysOperatLogMapper.insertSelective(record);
    }
}
