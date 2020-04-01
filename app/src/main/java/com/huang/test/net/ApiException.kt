package com.huang.test.net

/**
 * Created by xuhao on 2017/12/5.
 * desc:
 */
class ApiException : RuntimeException {

    public var code: Int? = null


    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}