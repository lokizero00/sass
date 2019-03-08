package com.loki.sass.common.code;

public abstract class RoleResultCode {

    //操作成功
    public final static String TRUE = "0000";

    //添加角色失败
    public final static String ROLE_ADD_ERROR = "role_0001";

    //删除角色失败
    public final static String ROLE_DELETE_ERROR = "rolen_0002";

    //修改角色失败
    public final static String ROLE_UPDATE_ERROR = "role_0003";

    //角色名称已存在
    public final static String ROLE_NAME_EXIST = "role_0004";

    //id对应的角色不存在
    public final static String ROLE_NOT_EXIST = "role_0005";

    //角色名称为空
    public final static String ROLE_NAME_EMPTY = "role_0006";

    //为角色更新权限失败
    public final static String ROLE_PERMISSION_OPERATE_ERROR = "role_0007";

    //查询的角色不都存在
    public final static String ROLE_NOT_ALL_EXIST = "role_0008";
}
