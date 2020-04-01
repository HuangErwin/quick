package com.huang.test.net

/**
 * Created by xuhao on 2017/12/5.
 * desc:
 */
object ErrorStatus {
    /**
     * 响应成功
     */
    @JvmField
    val SUCCESS = 0

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

   /**
     * 业务异常
     */
    @JvmField
    val USER_YEWU_ERROR = 400
    /**
     * 没有权限
     */
    @JvmField
    val USER_PERIMISON_ERROR = 401

   /**
     * 用户未登陆
     */
    @JvmField
    val USER_LOGIN_ERROR = 402


   /**
     * 服务器异常
     */
    @JvmField
    val USER_SERIVICE_ERROR = 500



    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005

}