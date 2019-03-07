package com.loki.sass.common.code;

public abstract class PermissionResultCode {

    //操作成功
    public final static String TRUE = "0000";

    //添加权限失败
    public final static String PERMISSION_ADD_ERROR = "permission_0001";

    //删除权限失败
    public final static String PERMISSION_DELETE_ERROR = "permission_0002";

    //修改权限失败
    public final static String PERMISSION_UPDATE_ERROR = "permission_0003";

    //权限名称已存在
    public final static String PERMISSION_NAME_EXIST = "permission_0004";

    //id对应的权限不存在
    public final static String PERMISSION_NOT_EXIST = "permission_0005";

    //待添加权限的名字为空
    public final static String PERMISSION_NAME_EMPTY = "permission_0006";
}
