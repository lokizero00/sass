package com.loki.sass.common.code;


/**
 * @ClassName: LoginResultCode
 * @Description: TODO 基础返回码
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class ZoneResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//小区名称重复
	public final static String ZONE_NAME_EXISTS = "zone_0001";
	//添加小区失败
	public final static String ZONE_ADD_ERROR = "zone_0002";
	//小区信息有误
	public final static String ZONE_DATA_INVALID = "zone_0003";
	//小区不存在
	public final static String ZONE_NOT_EXISTS = "zone_0004";
	//修改小区失败
	public final static String ZONE_EDIT_ERROR = "zone_0002";
}
