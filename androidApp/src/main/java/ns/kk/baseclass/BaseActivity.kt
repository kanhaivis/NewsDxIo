package ns.kk.baseclass

import ConstantItem.kNightMode
import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.IntentEvent

abstract class BaseActivity : AppCompatActivity() {

    abstract val setLayout : Int
    lateinit var topHembargarIcon : ImageView
    lateinit var topBack : ImageView
    lateinit var topIcon : ImageView
    lateinit var topProfile : ImageView

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout)

        topHembargarIcon = findViewById(R.id.top_hembargar_icon_press)
        topBack = findViewById(R.id.top_back_icon_press)
        topIcon = findViewById(R.id.top_icon_show)
        topProfile = findViewById(R.id.top_profile_icon_press)

        if (LocalStorage(this).getOnbord(kNightMode) != -1) {
            topHembargarIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            topBack.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            topIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

        topBack.setOnClickListener {
            onBackIconPress()
        }
        topProfile.setOnClickListener {
            IntentEvent.onTopBar_Profile(this)
        }

    }

    fun onTopBarShowHome(){
        topHembargarIcon.visibility = View.VISIBLE
        topIcon.visibility = View.VISIBLE
        topProfile.visibility = View.VISIBLE
    }

    fun onTopBarShowDetails(){
        topBack.visibility = View.VISIBLE
        topProfile.visibility = View.VISIBLE
    }

    fun onTopBarShowProfile(){
        topBack.visibility = View.VISIBLE
    }

    fun onBackIconPress() {
        finish()
    }

    fun onAddFragment(fragment: Fragment, addBackStack: Boolean, tag: String) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        if (addBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.fragment_container_tag, fragment, tag)
        ft.commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        val fragmentManage = supportFragmentManager
        val count = fragmentManage.backStackEntryCount
        if (count > 0) {
            fragmentManage.popBackStack()
            return
        }
        super.onBackPressed()
    }

}