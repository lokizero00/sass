package com.loki.sass.common.code;


/**
 * @ClassName: LoginResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class ResidentResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//注册信息有误
	public final static String REGISTER_DATA_INVALID = "resident_0001";
	//用户入驻失败
	public final static String REGISTER_JOIN_ERROR = "resident_0002";
	//此微信已注册
	public final static String WECHAT_EXISTS = "resident_0003";
	//访问区域不存在
	public final static String REGISTER_REGION_NOT_EXISTS = "resident_0004";
	//入驻申请不存在
	public final static String REGISTER_APPLY_NOT_EXISTS = "resident_0005";
	//入驻申请审批失败
	public final static String REGISTER_VERIFY_ERROR = "resident_0006";
	//审批数据有误
	public final static String REGISTER_VERIFY_DATA_INVALID = "resident_0007";
}
