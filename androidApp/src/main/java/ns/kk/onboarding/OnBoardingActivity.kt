package ns.kk.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ns.kk.newsdxio.android.R

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        findViewById<Button>(R.id.onboarding_next_btn).setOnClickListener {
//            intent = Intent(this@OnBoardingActivity, MainAppActivity::class.java)
//            startActivity(intent)
        }
    }
}