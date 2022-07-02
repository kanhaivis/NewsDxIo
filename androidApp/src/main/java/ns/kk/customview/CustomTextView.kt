package ns.kk.customview

import ConstantItem.kNightMode
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.TextureView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R

@SuppressLint("AppCompatCustomView")
class CustomTextView : TextView {

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
        if (LocalStorage((context as Activity)).getOnbord(kNightMode) == -1) {
           setTextColor(getResources().getColor(R.color.dn_black))
//            setBackgroundColor(getResources().getColor(R.color.dn_black))
        } else {
            setTextColor(getResources().getColor(R.color.dn_white))
        }
    }
}