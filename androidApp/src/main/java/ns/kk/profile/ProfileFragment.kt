package ns.kk.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ns.kk.baseclass.BaseFragment
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.android.R

class ProfileFragment: BaseFragment() {
    private val mainScope = MainScope()

    lateinit var firstName: TextView
    lateinit var lastName: TextView
    lateinit var phone: TextView
    lateinit var email: TextView

    override val setLayout: Int
        get() = R.layout.profile_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstName = view.findViewById(R.id.profile_first_name_txt)
        lastName = view.findViewById(R.id.profile_last_name_txt)
        phone = view.findViewById(R.id.profile_phone_txt)
        email = view.findViewById(R.id.profile_email_txt)

        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getUserProfile()
            }.onSuccess {

                val item = it.data
                item.phoneNumber?.let {
                    phone.visibility = View.VISIBLE
                    phone.text= it.toString()
                }
                item.email?.let {
                    email.visibility = View.VISIBLE
                    email.text= it.toString()
                }

            }.onFailure {
//                tv.text = "Error: ${it.localizedMessage}"
            }
        }

    }
}