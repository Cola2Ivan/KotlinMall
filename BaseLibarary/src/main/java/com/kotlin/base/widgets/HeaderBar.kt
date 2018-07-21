package com.kotlin.base.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.kotlin.base.R

class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var  isShowBack = true
    private var titleText:String? = null
    private var rightText:String? = null

    init {
        val typeArray = context.obtainStyledAttributes(R.styleable.HeaderBar)
        isShowBack = typeArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typeArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typeArray.getString(R.styleable.HeaderBar_rightText)
        initView()
        typeArray.recycle()
    }

    private fun initView() {

    }


}