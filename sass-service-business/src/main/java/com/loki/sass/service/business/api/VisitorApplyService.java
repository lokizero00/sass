package com.loki.sass.service.business.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.exception.BizException;

/**
 * created by lokizero00 on 2019-03-07
 */
public interface VisitorApplyService {
    boolean applyPass(Integer applyId,Integer adminId) throws BizException;
    boolean applyRefuse(Integer applyId,Integer adminId) throws BizException;
    PageInfo<UserResidentApplyDTO> getApplyListSearch(UserResidentApplyQueryVO userResidentApplyQueryVO) throws BizException;
}
