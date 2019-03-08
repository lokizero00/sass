package com.loki.sass.service.entrance.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorRecordDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.DoorRecordQueryVO;
import com.loki.sass.service.entrance.api.DoorRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-03-08
 */
@Slf4j
@RestController
@RequestMapping("/doorRecord")
public class DoorRecordController {
    @Autowired
    DoorRecordService doorRecordService;

    @RequestMapping(value = "v1/getDoorRecordListSearch",method = RequestMethod.POST)
    public ResultDTO<PageInfo<DoorRecordDTO>> getDoorRecordListSearch(@RequestParam String doorRecordQueryJson) throws BizException {
        ResultDTO<PageInfo<DoorRecordDTO>> result=new ResultDTO<>();
        DoorRecordQueryVO doorRecordQueryVO= JsonUtils.jsonToObject(doorRecordQueryJson,DoorRecordQueryVO.class);
        result.setSuccess(true);
        result.setModule(doorRecordService.getDoorRecordListBySearch(doorRecordQueryVO));
        return result;
    }
}
