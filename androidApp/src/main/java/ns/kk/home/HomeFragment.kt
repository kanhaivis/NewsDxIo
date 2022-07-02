package ns.kk.home

import ConstantItem.kNightMode
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import model.section.DATA
import model.section.WIDGET
import ns.kk.baseclass.BaseFragment
import ns.kk.customview.CustomTabLayout
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R

class HomeFragment : BaseFragment() {
    private val mainScope = MainScope()
    lateinit var viewPager : ViewPager
    private lateinit var tabLayout : CustomTabLayout
    lateinit var  mDynamicFragmentAdapter : DynamicFragmentAdapter


    override val setLayout: Int
        get() = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.home_viewpager)
        tabLayout = view.findViewById(R.id.home_tab_layout)

        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getSection()
            }.onSuccess {

                val home = DATA(SECTION_NAME = "Home")
                val list = arrayListOf<DATA>()
                list.add(home)
                it.DATA.addAll(0,list)
                onTabLayoutRender(it.DATA)
            }.onFailure {
//                tv.text = "Error: ${it.localizedMessage}"
            }
        }
        viewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout){
        })

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                println("")
                viewPager.currentItem = tab!!.position

                val tab_layout_text = tab!!.customView!!.findViewById<TextView>(R.id.tab_layout_text)
                tab_layout_text.setTextColor(resources.getColor(R.color.colorPrimary))
//                tab_layout_text.setBackgroundResource(R.drawable.tablayout_item_pressed)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                println("")

                val tab_layout_text = tab!!.customView!!.findViewById<TextView>(R.id.tab_layout_text)

                if (LocalStorage((context as Activity)).getOnbord(kNightMode) == -1) {
                    tab_layout_text.setTextColor(resources.getColor(R.color.dn_black))
                } else {
                    tab_layout_text.setTextColor(resources.getColor(R.color.dn_white))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                println("")
            }
        })
    }


    private fun onTabLayoutRender(data: List<DATA>){

        tabLayout.setTitle(data)
        mDynamicFragmentAdapter = DynamicFragmentAdapter(childFragmentManager, data.size,data)
        viewPager.adapter = mDynamicFragmentAdapter
        viewPager.currentItem =0
    }
}