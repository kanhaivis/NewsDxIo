package ns.kk.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import ns.kk.newsdxio.android.R

class CustomImageView : LinearLayout {

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defSysAttrs: Int) : super(
        context,
        attrs,
        defSysAttrs
    ) {
        initView(context)
    }

    private fun initView(context: Context) {
        setBackgroundColor(getResources().getColor(R.color.black));
    }
}