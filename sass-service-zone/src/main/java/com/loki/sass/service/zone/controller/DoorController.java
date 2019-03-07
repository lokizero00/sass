package com.loki.sass.service.zone.controller;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.DoorQueryVO;
import com.loki.sass.common.vo.DoorRequestVO;
import com.loki.sass.service.zone.api.DoorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by lokizero00 on 2019-02-17
 */

@Slf4j
@RestController
@RequestMapping("/door")
public class DoorController {
    @Autowired
    DoorService doorService;

    @RequestMapping(value = "v1/getDoorListSearch",method = RequestMethod.POST)
    public PageInfo<DoorDTO> getDoorListSearch(@RequestParam String doorQueryJson) throws BizException {
        DoorQueryVO doorQueryVO= JsonUtils.jsonToObject(doorQueryJson,DoorQueryVO.class);
        return doorService.getDoorListSearch(doorQueryVO);
    }

    @RequestMapping(value = "v1/addDoor",method = RequestMethod.POST)
    public Boolean addDoor(@RequestParam String doorRequestStr) throws BizException {
        DoorRequestVO doorRequestVO= JsonUtils.jsonToObject(doorRequestStr,DoorRequestVO.class);
        return doorService.addDoor(doorRequestVO);
    }

    @RequestMapping(value = "v1/editDoor",method = RequestMethod.POST)
    public Boolean editDoor(@RequestParam String doorRequestStr) throws BizException {
        DoorRequestVO doorRequestVO= JsonUtils.jsonToObject(doorRequestStr,DoorRequestVO.class);
        return doorService.editDoor(doorRequestVO);
    }

    @RequestMapping(value = "v1/deleteDoor",method = RequestMethod.POST)
    public Boolean deleteDoor(@RequestParam Integer doorId,@RequestParam Integer adminId) throws BizException {
        return doorService.deleteDoor(doorId,adminId);
    }
}
