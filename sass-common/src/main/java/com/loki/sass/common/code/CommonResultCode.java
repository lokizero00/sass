package com.loki.sass.common.code;


/**
 * @ClassName: CommonResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class CommonResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//发生了未知错误，请重新操作或者联系客服人员!
	public final static String COMMON_SERVICE_ERROR = "common_0099";
	//登录失效，请重新登录
	public final static String COMMON_LOGIN_TIMEOUT= "common_0001";
	//无权限操作
	public final static String COMMON_UNAUTH= "common_0002";
}
