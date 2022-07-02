package ns.kk.splace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import ns.kk.newsdxio.android.R
import ns.kk.onboarding.OnBoardingActivity

class SplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace)

       object : CountDownTimer(1000, 500) {
           override fun onTick(millisUntilFinished: Long) {}
           override fun onFinish() {
                intent = Intent(this@SplaceActivity, OnBoardingActivity::class.java)
               startActivity(intent)
           }
       }.start()
    }
}