package com.loki.sass.service.business.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.UserResidentApplyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.UserResidentApplyQueryVO;

/**
 * created by lokizero00 on 2019-03-07
 */
public interface UserResidentApplyService {
    boolean applyPass(Integer applyId,Integer adminId) throws BizException;
    boolean applyRefuse(Integer applyId,Integer adminId) throws BizException;
    PageInfo<UserResidentApplyDTO> getApplyListSearch(UserResidentApplyQueryVO userResidentApplyQueryVO) throws BizException;
}
