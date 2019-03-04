package com.loki.sass.service.utils.util.sms.ali;


import java.io.Serializable;

/**
 * Created by lokizero00
 */
public interface ITtsRequest extends Serializable{

    public void setCalledNum(String recNum);

    public void setTtsParam(String random);

    public void setSmsTemplateCode(String smsTemplateCode);

    public String getExtend();

    public String getCalledShowNum();

    public String getCalledNum();

    public String getTtsParam();

    public String getTtsCode();

}
