package com.loki.sass.feignclient.feignService;

import com.github.pagehelper.PageInfo;
import com.loki.sass.common.dto.ZoneDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by lokizero00 on 2019-02-20
 */
@FeignClient("sass-service-zone")
public interface FeignZoneService {
    @RequestMapping(method = RequestMethod.POST, value = "/zone/v1/getZoneListSearch")
    PageInfo<ZoneDTO> getZoneListSearch(@RequestParam("zoneQueryJson") String zoneQueryJson);
}
