package com.loki.sass.service.sysconfig.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loki.sass.common.code.TipResultCode;
import com.loki.sass.common.exception.BizException;
import com.loki.sass.domain.mapper.TipInfoMapper;
import com.loki.sass.domain.model.TipInfo;
import com.loki.sass.domain.model.TipInfoExample;
import com.loki.sass.service.sysconfig.api.TipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * created by lokizero00 on 2019-02-15
 */
@Slf4j
@Service
public class TipInfoServiceImpl implements TipInfoService {
    @Autowired
    private TipInfoMapper mapper;

    @Override
    public PageInfo<TipInfo> findByPage(Integer page, Integer rows, String searchVal) {
        //分页
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(rows)) {
            PageHelper.startPage(page, rows);
        }

        List<TipInfo> list=mapper.selectBySearch(searchVal);
        PageInfo<TipInfo> pageInfo = new PageInfo<TipInfo>(list);
        return pageInfo;
    }

    @Override
    public Integer insertIt(TipInfo record) throws BizException {
        TipInfo lastInfo=this.getItByCode(record.getTipCode());
        if(lastInfo!=null){
            throw new BizException(TipResultCode.TIP_CODE_EXIST);
        }
        int row = this.mapper.insert(record);
        if (row > 0) {
            int id=record.getId();
            return id;
        }

        throw new BizException("10001");
    }

    @Override
    public Boolean updateIt(TipInfo record) throws BizException {
        int row = this.mapper.updateByPrimaryKey(record);
        if(row>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteIt(Integer Id) throws BizException {
        return false;
    }

    @Override
    public TipInfo getIt(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public TipInfo getItByCode(String code) {
        TipInfoExample example=new TipInfoExample();
        example.createCriteria().andTipCodeEqualTo(code);
        List<TipInfo> list=this.mapper.selectByExample(example);
        TipInfo tipInfo=null;
        if(list!=null && !list.isEmpty()){
            tipInfo=list.get(0);
        }
        return tipInfo;
    }

    @Override
    public List<TipInfo> getAll() {
        TipInfoExample example=new TipInfoExample();
        List<TipInfo> list=this.mapper.selectByExample(example);
        return list;
    }
}
