package com.loki.sass.common.code;


/**
 * @ClassName: AuthResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class AuthResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//token参数为空
	public final static String AUTH_NULL_ERROR = "auth_0001";
	//token校验失败
	public final static String AUTH_VERIFY_ERROR = "auth_0002";
}
