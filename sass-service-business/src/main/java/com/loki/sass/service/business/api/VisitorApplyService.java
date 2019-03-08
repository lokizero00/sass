package com.loki.sass.service.business.api;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.VisitorApplyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.VisitorApplyQueryVO;

/**
 * created by lokizero00 on 2019-03-07
 */
public interface VisitorApplyService {
    boolean applyPass(Integer applyId,String reason,Integer adminId) throws BizException;
    boolean applyRefuse(Integer applyId,String reason,Integer adminId) throws BizException;
    PageInfo<VisitorApplyDTO> getApplyListSearch(VisitorApplyQueryVO visitorApplyQueryVO) throws BizException;
}
