package ns.kk.customview

import ConstantItem.kNightMode
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R

class CustomScrollView : ScrollView {

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
        if (LocalStorage((context as Activity)).getOnbord(kNightMode) != -1) {
            setBackgroundColor(getResources().getColor(R.color.dn_black))
        } else {
            setBackgroundColor(getResources().getColor(R.color.dn_white))
        }
    }
}