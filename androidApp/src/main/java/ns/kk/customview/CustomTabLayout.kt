package ns.kk.customview

import ConstantItem.kNightMode
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import model.section.DATA
import ns.kk.model.Data
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R


class CustomTabLayout : TabLayout {

    private var titles: List<Data>? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defSysAttrs: Int) : super(
        context,
        attrs,
        defSysAttrs
    ) {
        initView()
    }


    private fun initView(){
        titles = ArrayList()
        if (LocalStorage((context as Activity)).getOnbord(kNightMode) != -1) {
            setBackgroundColor(getResources().getColor(R.color.dn_black))
        } else {
            setBackgroundColor(getResources().getColor(R.color.dn_white))
        }
    }

    fun setTitle(titlesList: List<DATA>?) {
        this.titles = titles

        for (title in titlesList!!) {
            val tab = newTab()
            tab.setCustomView(R.layout.tablayout_item)
            if (tab.customView != null) {
                val text =
                    tab.customView!!.findViewById<TextView>(R.id.tab_layout_text)
                    text.text = title.SECTION_NAME
            }
            this.addTab(tab)
        }
    }
}