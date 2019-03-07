package com.loki.sass.common.code;

public abstract class AdminResultCode {

    //操作成功
    public final static String TRUE = "0000";

    //用户名已存在
    public final static String ADMIN_USERNAME_EXIST = "admin_0001";

    //添加用户失败
    public final static String ADMIN_ADD_ERROR = "admin_0002";

    //手机号有误
    public final static String ADMIN_MOBILE_WRONG = "admin_0003";

    //传参为空
    public final static String ADMIN_IS_NULL = "admin_0004";

    //用户名为空
    public final static String ADMIN_USERNAME_EMPTY = "admin_0005";

    //删除异常
    public final static String ADMIN_DELETE_ERROR = "admin_0006";

    //更新异常
    public final static String ADMIN_UPDATE_ERROR = "admin_0007";

    //操作者找不到
    public final static String ADMIN_OPERATOR_NOT_EXIST = "admin_0008";
}
