package ns.kk.newsdxio

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ns.kk.baseclass.BaseActivity
import ns.kk.home.HomeFragment
import ns.kk.myfeed.MyFeedFragment
import ns.kk.newsdxio.android.R
import ns.kk.setting.SettingFragment
import ns.kk.tranding.TrandingFragment
import ns.kk.utils.IntentEvent

class NewsDxIoMainScreen : BaseActivity() {

    override val setLayout: Int
        get() = R.layout.activity_newsdx_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onTopBarShowHome()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigationView.selectedItemId = R.id.item_home
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.item_home -> {
                onAddFragment(HomeFragment(),false,"home_tag")
                return@OnNavigationItemSelectedListener true
            }
            R.id.item_myfeed -> {
                onAddFragment(MyFeedFragment(),false,"home_tag")
                return@OnNavigationItemSelectedListener true
            }
            R.id.item_more -> {
                onAddFragment(SettingFragment(),false,"home_tag")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}