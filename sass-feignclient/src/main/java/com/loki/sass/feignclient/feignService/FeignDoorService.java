package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.DoorDTO;
import com.loki.sass.common.exception.BizException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-zone")
public interface FeignDoorService {
    @RequestMapping(method = RequestMethod.POST, value = "/door/v1/getDoorListSearch")
    PageInfo<DoorDTO> getDoorListSearch(@RequestParam("doorQueryJson") String doorQueryJson) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/door/v1/addDoor")
    Boolean addDoor(@RequestParam("doorRequestStr") String doorRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/door/v1/editDoor")
    Boolean editDoor(@RequestParam("doorRequestStr") String doorRequestStr) throws BizException;

    @RequestMapping(method = RequestMethod.POST,value = "/door/v1/deleteDoor")
    Boolean deleteDoor(@RequestParam("doorId") Integer doorId, @RequestParam("adminId") Integer adminId) throws BizException;
}
