package com.loki.sass.common.code;


/**
 * @ClassName: LoginResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class LoginResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//手机号为空
	public final static String LOGIN_MOBILE_NULL_ERROR = "login_0001";
	//密码为空
	public final static String LOGIN_PASSWORD_NULL_ERROR = "login_0002";
	//用户不存在
	public final static String LOGIN_USER_INEXIST_ERROR = "login_0003";
	//登录失败
	public final static String LOGIN_SERVICE_ERROR = "login_0004";
	//密码错误
	public final static String LOGIN_PASSWORD_VERIFY_ERROR = "login_0005";
	//验证码为空
	public final static String LOGIN_SMSCODE_NULL_ERROR = "login_0006";
	//账号已停用
	public final static String LOGIN_USER_DISABLE = "login_0007";
}
