package com.loki.sass.service.utils.util.sms.ali;

import lombok.Getter;
import lombok.Setter;


/**
 * Created by lokizero00.
 */
public abstract class AbstractSmsRequest {
    /**
     * 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
     */
    @Getter
    private String extend;

    /**
     * 短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
     */
    @Setter
    @Getter
    private String recNum;

    /**
     * 短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
     */
    @Setter
    @Getter
    protected String smsFreeSignName;

    /**
     * 短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
     */
    @Getter
    @Setter
    private String smsParam;

    /**
     * 短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
     */
    @Getter
    @Setter
    private String smsTemplateCode;

    /**
     * 短信类型，传入值请填写normal
     */
    @Getter
    private String smsType = "normal";
}