package ns.kk.subscription

import ConstantItem.kNightMode
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import ns.kk.baseclass.BaseActivity
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R

class SubscriptionActivity : BaseActivity(){

    lateinit var viewPager2: ViewPager2
    lateinit var alpineIcon : ImageView

    override val setLayout: Int
        get() = R.layout.activity_subscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onTopBarShowDetails()

        viewPager2 = findViewById(R.id.subscription_viewpager2)
        alpineIcon = findViewById(R.id.alpine_icon)

        if (LocalStorage(this).getOnbord(kNightMode) != -1) {
            alpineIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

        val adapterSub = SubscriptionAdapter(this, getSubList())
        viewPager2.adapter = adapterSub

    }

}

fun getSubList() : ArrayList<Subsc>{
    val subList = arrayListOf<Subsc>()
    subList.add(Subsc("20% off all product","4.9", "1 week"))
    subList.add(Subsc("30% off all product","10.9", "2 week"))
    subList.add( Subsc("40% off all product","30.0", "3 week"))
    return subList
}



data class Subsc(val detail: String, val price: String, val duration: String)