package com.huang.test.net;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.huang.test.Constants;
import com.huang.test.utils.Cache;
import com.huang.test.utils.RouterManger;

/**
 * 登录跳转拦截器
 */
@Interceptor(priority = 8, name = "登录跳转拦截器")
public class LoginInterceptor implements IInterceptor {

    Context mContext;

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        if (Constants.NEED_LOGIN == postcard.getExtra() && TextUtils.isEmpty(Cache.newInstance().getString(Constants.USER_TOKEN))) {
            MainLooper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //如果需要再界面展示东西，需要切换到主线程进行操作
//                    Toast.makeText(mContext, "请登录", Toast.LENGTH_SHORT).show();

                        ARouter.getInstance().build(RouterManger.USER_NEED_LOGIN).navigation();
                        callback.onInterrupt(null);

                    //处理完成，交还控制权

                }
            });
        } else {

            //处理完成，交还控制权
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
        //此处做一些初始化的工作
    }
}
