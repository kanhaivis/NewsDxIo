package ns.kk.profile

import android.os.Bundle
import ns.kk.baseclass.BaseActivity
import ns.kk.callback.OnScreenEvent
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantItem.SCREEN_OTP
import ns.kk.utils.ConstantItem.SCREEN_PROFILE
import ns.kk.utils.ConstantItem.SCREEN_SIGNIN

class ProfileActivity: BaseActivity() , OnScreenEvent {

    override val setLayout: Int
        get() = R.layout.profile_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTopBarShowProfile()
        onAddFragment(SignInFragment(this), false, "signin")
    }

    override fun onScreenName(screenName: String) {
        // call back
        when(screenName) {
            SCREEN_SIGNIN -> onAddFragment(SignInFragment(this), false, "signin")
            SCREEN_OTP -> onAddFragment(OTPFragment(this), false, "otp")
            SCREEN_PROFILE -> onAddFragment(ProfileFragment(), false, "profile")
        }
    }
}

