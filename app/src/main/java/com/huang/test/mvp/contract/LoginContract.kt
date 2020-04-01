package com.huang.test.mvp.contract

import com.huang.test.base.IBaseView
import com.huang.test.bean.BaseBean
import okhttp3.RequestBody

/**
 * Created by EDZ on 2020/3/4.
 */
interface LoginContract {
    interface View : IBaseView {
        fun showError(msg: String, errorCode: Int)
        fun setPhoneCode(usbean: BaseBean)
    }
    interface Presenter {
        fun getPhoneCode(requestBody:RequestBody)
    }
}