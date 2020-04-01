package com.huang.test.view

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.huang.test.R

/**
 * Created by Aaron on 2019-07-18.
 *
 * 自定义toast view
 */
class ToastView : FrameLayout {

    private var toastText: TextView? = null

    constructor(context: Context) : super(context) {

        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        init(context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
            context,
            attrs,
            defStyleAttr,
            defStyleRes
    ) {

        init(context)
    }

    private fun init(context: Context) {
        addView(View.inflate(context, R.layout.toast_view, null))
        toastText = findViewById(R.id.toastText)
    }

    fun setText(text: String) {
        toastText!!.text = text
    }

    fun setTextSize(size: Int) {
        toastText!!.textSize = size.toFloat()
    }

    fun setTextColor(color: Int) {
        toastText!!.setTextColor(color)
    }
}