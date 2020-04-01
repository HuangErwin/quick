package com.huang.test.net

import com.huang.test.bean.BaseBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.math.BigDecimal


/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService {


//TODO 首页---start------------------------------------------------------------------------------------------------------------


    /**
     * 上传图片
     */
    @Multipart
    @POST("common/file/upload")
    fun uploadFile(@Part file: MultipartBody.Part): Observable<BaseBean>

    /**
     * 首页banner
     */
    @GET("config/banner/list?")
    fun getFirstHomeData(@Query("bannerAreaId") bannerAreaId: Int): Observable<BaseBean>

    /**
     * 更新信息接口
     */
    @POST("user/cmUser/update")
    fun updateInfomation(@Body requestBody: RequestBody): Observable<BaseBean>

    fun getPhoneCode(requestBody: RequestBody):Observable<BaseBean>


}