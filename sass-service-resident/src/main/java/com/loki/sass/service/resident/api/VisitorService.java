package com.loki.sass.service.resident.api;

import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.VisitApplyRequestVO;

/**
 * created by lokizero00 on 2019-02-27
 */
public interface VisitorService {
    boolean visitApply(VisitApplyRequestVO visitApplyRequestVO) throws BizException;
}
