package com.loki.sass.common.code;


/**
 * @ClassName: LoginResultCode
 * @Description: TODO 基础返回码
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class VisitorResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//访问区域不存在
	public final static String VISIT_REGION_NOT_EXISTS = "visitor_0001";
	//访问人不存在
	public final static String VISIT_INTERVIEWEE_NOT_EXISTS = "visitor_0002";
	//来访申请失败
	public final static String VISIT_APPLY_ERROR = "visitor_0003";
	//来访信息有误
	public final static String VISIT_DATA_INVALID = "visitor_0004";
}
