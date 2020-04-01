package com.huang.test.mvp.presenter

import com.huang.test.base.BasePresenter
import com.huang.test.mvp.contract.LoginContract
import com.huang.test.mvp.model.LoginModel
import com.huang.test.net.ExceptionHandle
import okhttp3.RequestBody

/**
 * Created by EDZ on 2020/3/4.
 */

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    private val loginModel: LoginModel by lazy {
        LoginModel()
    }

    /**
     * 获取用户信息
     */
    override fun getPhoneCode(requestBody:RequestBody) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = loginModel.getPhoneCode(requestBody).subscribe({ usbean ->
            mRootView?.apply {
                dismissLoading()
                setPhoneCode(usbean)
            }
        }, { t ->
            mRootView?.apply {
                dismissLoading()
                showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
            }
        })
        addSubscription(disposable)
    }



}