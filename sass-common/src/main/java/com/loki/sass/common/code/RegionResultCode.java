package com.loki.sass.common.code;


/**
 * @ClassName: RegionResultCode
 * @date 2018年3月11日 上午10:14:38
 *
 *
 */
public abstract class RegionResultCode {

	//操作成功！
	public final static String TRUE = "0000";
	//区域名称重复
	public final static String REGION_NAME_EXISTS = "region_0001";
	//添加区域失败
	public final static String REGION_ADD_ERROR = "region_0002";
	//区域信息有误
	public final static String REGION_DATA_INVALID = "region_0003";
	//区域不存在
	public final static String REGION_NOT_EXISTS = "region_0004";
	//修改区域失败
	public final static String REGION_EDIT_ERROR = "region_0005";
	//区域ID不正确
	public final static String REGION_ID_INVALID = "region_0006";
	//删除区域失败
	public final static String REGION_DELETE_ERROR = "region_0007";
	//父区域不存在
	public final static String REGION_PARENT_NOT_EXISTS = "region_0008";
}
