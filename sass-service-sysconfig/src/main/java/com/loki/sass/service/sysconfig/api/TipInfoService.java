package com.loki.sass.service.sysconfig.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.domain.model.TipInfo;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-15
 */
public interface TipInfoService {
    public PageInfo<TipInfo> findByPage(Integer page, Integer rows, String searchVal);
    public Integer insertIt(TipInfo record) throws BizException;
    public Boolean updateIt(TipInfo record) throws BizException;
    public Boolean deleteIt(Integer Id) throws BizException;
    public TipInfo getIt(Integer id);
    public TipInfo getItByCode(String code);
    public List<TipInfo> getAll();
}
