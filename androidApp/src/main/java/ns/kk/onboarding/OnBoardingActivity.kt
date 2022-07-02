package ns.kk.onboarding

import ConstantItem.kOnboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.IntentEvent
import ns.kk.utils.IntentEvent.goNewsDxInMain

class OnBoardingActivity : AppCompatActivity() {
    private val mainScope = MainScope()
    lateinit var viewPager: ViewPager2
    lateinit var dotIndicator : TabLayout
    lateinit var nextBtn : TextView
     var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        viewPager = findViewById(R.id.onboarding_pageview2)
        dotIndicator = findViewById(R.id.dot_tab_layout)
        nextBtn = findViewById(R.id.onboarding_next_btn)

        val adapter =OnBoardingPageViewAdapter(this, listOf())
        viewPager.adapter = adapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


                if (count != position) {
                    nextBtn.visibility = View.INVISIBLE
                } else {
                    nextBtn.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        TabLayoutMediator(dotIndicator, viewPager) { tab, position ->
        }.attach()



        nextBtn.setOnClickListener {
            LocalStorage(this).putOnbord(kOnboard,1)
            goNewsDxInMain(this)
        }
        findViewById<TextView>(R.id.onboarding_skip_btn).setOnClickListener {
            LocalStorage(this).putOnbord(kOnboard,1)
            goNewsDxInMain(this)
        }

        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getOnBoarding()
            }.onSuccess {
                count = it.DATA.size
                count--
                adapter.onUpdateData(it.DATA)
            }.onFailure {
//                tv.text = "Error: ${it.localizedMessage}"
            }
        }
    }
}