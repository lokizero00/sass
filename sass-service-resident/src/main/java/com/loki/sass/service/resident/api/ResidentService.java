package com.loki.sass.service.resident.api;

import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.vo.ResidentJoinRequestVO;

/**
 * created by lokizero00 on 2019-02-27
 */
public interface ResidentService {
    boolean joinApply(ResidentJoinRequestVO residentJoinRequestVO) throws BizException;

}
