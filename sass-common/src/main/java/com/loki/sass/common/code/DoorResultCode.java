package com.loki.sass.common.code;


/**
 * @ClassName: DoorResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class DoorResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//门禁名称重复
	public final static String DOOR_NAME_EXISTS = "door_0001";
	//添加门禁失败
	public final static String DOOR_ADD_ERROR = "door_0002";
	//门禁信息有误
	public final static String DOOR_DATA_INVALID = "door_0003";
	//门禁不存在
	public final static String DOOR_NOT_EXISTS = "door_0004";
	//修改门禁失败
	public final static String DOOR_EDIT_ERROR = "door_0005";
	//门禁ID不正确
	public final static String DOOR_ID_INVALID = "door_0006";
	//删除门禁失败
	public final static String DOOR_DELETE_ERROR = "door_0007";
}
