package com.loki.sass.service.zone.controller;

import com.loki.sass.common.dto.RegionDTO;
import com.loki.sass.common.dto.ResultDTO;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.common.util.JsonUtils;
import com.loki.sass.common.vo.RegionRequestVO;
import com.loki.sass.service.zone.api.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by lokizero00 on 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionService regionService;

    @RequestMapping(value = "v1/addRegion",method = RequestMethod.POST)
    public ResultDTO<Boolean> addRegion(@RequestParam String regionRequestStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        RegionRequestVO regionRequestVO= JsonUtils.jsonToObject(regionRequestStr,RegionRequestVO.class);
        result.setSuccess(regionService.addRegion(regionRequestVO));
        return result;
    }

    @RequestMapping(value = "v1/editRegion",method = RequestMethod.POST)
    public ResultDTO<Boolean> editRegion(@RequestParam String regionRequestStr) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        RegionRequestVO regionRequestVO= JsonUtils.jsonToObject(regionRequestStr,RegionRequestVO.class);
        result.setSuccess(regionService.editRegion(regionRequestVO));
        return result;
    }

    @RequestMapping(value = "v1/deleteRegion",method = RequestMethod.POST)
    public ResultDTO<Boolean> deleteRegion(@RequestParam Integer regionId,@RequestParam Integer adminId) throws BizException {
        ResultDTO<Boolean> result=new ResultDTO<>();
        result.setSuccess(regionService.deleteRegion(regionId,adminId));
        return result;
    }

    @RequestMapping(value = "v1/getRegionListByParentId",method = RequestMethod.POST)
    public ResultDTO<List<RegionDTO>> getRegionListByParentId(@RequestParam Integer regionId) throws BizException {
        ResultDTO<List<RegionDTO>> result=new ResultDTO<>();
        result.setSuccess(true);
        result.setModule(regionService.getRegionListByParentId(regionId));
        return result;
    }

    @RequestMapping(value = "v1/getRootRegionList",method = RequestMethod.POST)
    public ResultDTO<List<RegionDTO>> getRootRegionListByZoneId(@RequestParam Integer adminId) throws BizException {
        ResultDTO<List<RegionDTO>> result=new ResultDTO<>();
        result.setSuccess(true);
        result.setModule(regionService.getRootRegionList(adminId));
        return result;
    }
}
