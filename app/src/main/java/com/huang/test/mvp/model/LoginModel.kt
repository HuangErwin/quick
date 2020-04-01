package com.huang.test.mvp.model

import com.huang.test.mvp.scheduler.SchedulerUtils
import com.huang.test.bean.BaseBean
import com.huang.test.net.RetrofitManager
import io.reactivex.Observable
import okhttp3.RequestBody

/**
 * Created by EDZ on 2020/3/4.
 */
class LoginModel {


    fun getPhoneCode(requestBody: RequestBody): Observable<BaseBean> {
        return RetrofitManager.service.getPhoneCode(requestBody)
                .compose(SchedulerUtils.ioToMain())
    }




}