package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorRecordDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.dto.VisitorApplyDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.DoorRecordQueryVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-03-07
 */
@FeignClient("sass-service-entrance")
public interface FeignDoorRecordService {

    @RequestMapping(value = "/doorRecordv1/getDoorRecordListSearch",method = RequestMethod.POST)
    ResultDTO<PageInfo<DoorRecordDTO>> getDoorRecordListSearch(@RequestParam("doorRecordQueryJson") String doorRecordQueryJson) throws BizException;
}
