package com.huang.test.utils

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.huang.test.MyApplication
import com.huang.test.R
import com.huang.test.view.ToastView

/**
 *
 * 弹出toast提示工具类
 */
object ToastUtils {

    private var mToast: Toast? = null  //toast样式
    private val mMsg: String? = null  //上一次弹出的内容
    private var mToastView: ToastView? = null  //自定义view
    private var mToastGravity:Int = -1  //位置

    /**
     * 弹出提示
     * @param msg  提示信息
     * @param time  显示时间
     */
    fun showToast(msg: String?, time: Int, context: Context?) {
        if (mToast == null || mMsg != null && msg != mMsg) {
            mToast = Toast.makeText(context, msg, time)
            if (mToastView != null) {
                mToast!!.view = mToastView
                mToastView!!.setText(msg!!)
            } else {
                mToast!!.setText(msg)
            }
        } else {
            if (mToastView != null && mToast!!.view != mToastView) {
                mToast!!.view = mToastView
            }
            if (mToastView != null) {
                mToastView!!.setText(msg!!)
            } else {
                mToast!!.setText(msg)
            }
            mToast!!.duration = time
        }
        if (mToastGravity != -1) {
            mToast!!.setGravity(mToastGravity, 0, 0)
        }

        //不设置的话，最高显示到状态栏下面
        mToast!!.view.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        mToast!!.show()
    }

    /**
     * 弹出提示信息
     * @param msgId  提示信息id
     * @param time  显示时间
     */
    fun showToast(msgId: Int, time: Int, context: Context?) {
        showToast(context?.getString(msgId), time, context)
    }


    /**
     * 弹出自定义 收藏的toast  且居中
     */
    fun showCustomToast(msgId: String,  context: Context?) {
        var toast= Toast(MyApplication.context)
        //创建一个填充物,用于填充Toast
        var inflater = LayoutInflater.from(context)
        toast.setGravity(Gravity.CENTER,0,0)
        //填充物来自的xml文件,在这个改成一个view
        //实现xml到view的转变哦
        var view =inflater.inflate(R.layout.custom_toast,null)
        view.findViewById<TextView>(R.id.tv_content).text = msgId
        //把填充物放进toast
        toast.setView(view)
        toast.setDuration(Toast.LENGTH_SHORT)

        //展示toast
        toast.show()
    }

    /**
     * 弹出短时间提示
     * @param msg  提示信息
     */
    fun showShortToast(msg: String, context: Context?) {
        showToast(msg, Toast.LENGTH_SHORT, context)
    }

    fun showShortToast(msgId:Int, context: Context?) {
        showToast(msgId, Toast.LENGTH_SHORT, context)
    }

    /**
     * 弹出长时间提示
     * @param msg  提示信息
     */
    fun showLongToast(msg: String, context: Context?) {
        showToast(msg, Toast.LENGTH_LONG, context)
    }

    /**
     * 关闭当前Toast
     */
    fun cancelCurrentToast() {
        if (mToast != null) {
            mToast!!.cancel()
        }
    }

    fun reToast(msg: String) {
        Toast.makeText(MyApplication.context, msg, Toast.LENGTH_SHORT).show()
    }

    fun reToast(msgId: Int) {
        Toast.makeText(MyApplication.context, msgId, Toast.LENGTH_SHORT).show()
    }



    fun setToastGravity(gravity: Int) {
        mToastGravity = gravity
    }

    /**
     * 重置toast 信息
     */
    fun resetToast() {
        mToastView = null
        mToastGravity = -1
        mToast = null
    }


}