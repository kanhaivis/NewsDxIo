package ns.kk.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import ns.kk.baseclass.BaseFragment
import ns.kk.callback.OnScreenEvent
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantItem.SCREEN_OTP

class SignInFragment(private val screenEvent: OnScreenEvent): BaseFragment() {

    lateinit var screenListener : OnScreenEvent

    override val setLayout: Int
        get() = R.layout.signin_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenListener = screenEvent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         view.findViewById<Button>(R.id.signIn_btn_press).setOnClickListener {
             screenListener.onScreenName(SCREEN_OTP)
         }
    }
}