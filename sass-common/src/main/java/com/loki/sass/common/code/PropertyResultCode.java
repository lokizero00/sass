package com.loki.sass.common.code;


/**
 * @ClassName: PropertyResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class PropertyResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//物业名称重复
	public final static String PROPERTY_NAME_EXISTS = "property_0001";
	//添加物业失败
	public final static String PROPERTY_ADD_ERROR = "property_0002";
	//物业信息有误
	public final static String PROPERTY_DATA_INVALID = "property_0003";
	//物业不存在
	public final static String PROPERTY_NOT_EXISTS = "property_0004";
	//修改物业失败
	public final static String PROPERTY_EDIT_ERROR = "property_0005";
	//物业ID不正确
	public final static String PROPERTY_ID_INVALID = "property_0006";
	//删除物业失败
	public final static String PROPERTY_DELETE_ERROR = "property_0007";
}
