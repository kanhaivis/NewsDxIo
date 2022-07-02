package ns.kk.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import ns.kk.baseclass.BaseActivity
import ns.kk.baseclass.BaseFragment
import ns.kk.callback.OnScreenEvent
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantItem

class OTPFragment(val screenEvent: OnScreenEvent): BaseFragment() {
    override val setLayout: Int
        get() = R.layout.otp_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.verify_btn_press).setOnClickListener {
            screenEvent.onScreenName(ConstantItem.SCREEN_PROFILE)
        }
    }
}