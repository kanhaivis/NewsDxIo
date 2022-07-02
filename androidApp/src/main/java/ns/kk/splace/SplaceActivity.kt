package ns.kk.splace

import ConstantItem.kNightMode
import ConstantItem.kOnboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.IntentEvent.goNewsDxInMain
import ns.kk.utils.IntentEvent.onOnBoarding

class SplaceActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace)


        if (LocalStorage(this).getNightMode(kNightMode) == -1) {

        }

        if (LocalStorage(this).getOnbord(kOnboard) == -1) {
            Handler().postDelayed({
                onOnBoarding(this)
            }, SPLASH_TIME_OUT)
        } else {
            Handler().postDelayed({
                goNewsDxInMain(this)
            }, SPLASH_TIME_OUT)
        }
    }
}