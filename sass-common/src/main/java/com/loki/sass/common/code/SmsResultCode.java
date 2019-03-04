package com.loki.sass.common.code;


/**
 * @ClassName: SmsResultCode
 * @Description: TODO 基础返回码
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class SmsResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//验证码输入有误，请重新输入
	public final static String COMMON_VERIFY_CODE_ERROR = "sms_0001";
	//短信发送失败
	public final static String SEND_SMS_ERROR = "sms_0002";
	//验证码已失效，请重新获取验证码
	public final static String SMS_OUT_VALID_TIME = "sms_0003";
	//短信发送间隔时间太短
	public final static String SMS_DURATION_ERROR = "sms_0005";
	//单日短信发送次数超过10条
	public final static String SMS_TODAY_LIMIT_ERROR = "sms_0006";
	//数据不存在
	public final static String COMMON_NO_DATA = "sms_0007";
	//发生了未知错误，请重新操作或者联系客服人员
	public final static String COMMON_SERVICE_ERROR = "sms_0099";
}
