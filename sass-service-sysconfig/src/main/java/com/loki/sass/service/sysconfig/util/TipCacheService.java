package com.loki.sass.service.sysconfig.util;

import com.loki.sass.common.exception.BizException;
import com.loki.sass.domain.model.TipInfo;
import com.loki.sass.service.sysconfig.api.TipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-15
 */

@Slf4j
@Service
public class TipCacheService {
    public static final String CACHE_NAME="tipInfo";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TipInfoService tipInfoService;

    /**
     * 保存键值对数据到Cache
     * @param code
     * @param msg
     */
    public void putIt(String code,String msg){
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(CACHE_NAME);
        ops.put(code, msg);
    }


    /**
     * 重新装载消息提示到Cache
     * @throws BizException
     */
    public void reload() throws BizException {
        List<TipInfo> list=this.tipInfoService.getAll();
        if(list==null || list.isEmpty()){
            return ;
        }

        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(CACHE_NAME);
        for(TipInfo tip:list){
            ops.put(tip.getTipCode(), tip.getTipMsg());
        }

    }

    /**
     * 获取对应编码的消息提示信息
     * @param code
     * @return
     * @throws BizException
     */
    public String getTipMsg(String code) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(CACHE_NAME);
        String msg=ops.get(code);
        if(msg==null || msg.isEmpty()){
            TipInfo sysTipInfo=tipInfoService.getItByCode(code);
            if(sysTipInfo!=null){
                msg=sysTipInfo.getTipMsg();
                this.putIt(code, msg);
            }
        }
        return msg;
    }
}
