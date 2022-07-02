package ns.kk.setting

import ConstantItem.kNightMode
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import ns.kk.baseclass.BaseFragment
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.IntentEvent

class SettingFragment : BaseFragment() {

    lateinit var darkmodeSwitch : SwitchCompat
    lateinit var view1Dp1 : View
    lateinit var view1Dp2 : View
    lateinit var view1Dp3 : View
    lateinit var view1Dp4 : View
    lateinit var view1Dp5 : View
    lateinit var settingIoc : ImageView
    lateinit var bookmarkTxt : TextView

    override val setLayout: Int
        get() = R.layout.setting_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookmarkTxt = view.findViewById(R.id.more_bookmark_press)
        settingIoc = view.findViewById(R.id.setting_icon)
        view1Dp1 = view.findViewById(R.id.view1)
        view1Dp2 = view.findViewById(R.id.view2)
        view1Dp3 = view.findViewById(R.id.view3)
        view1Dp4 = view.findViewById(R.id.view4)
        view1Dp5 = view.findViewById(R.id.view5)
        darkmodeSwitch = view.findViewById(R.id.customSwitch_darkmode)

        if (LocalStorage(requireActivity()).getNightMode(kNightMode) != -1) {
            view1Dp1.setBackgroundColor(resources.getColor(R.color.dn_white))
            view1Dp2.setBackgroundColor(resources.getColor(R.color.dn_white))
            view1Dp3.setBackgroundColor(resources.getColor(R.color.dn_white))
            view1Dp4.setBackgroundColor(resources.getColor(R.color.dn_white))
            view1Dp5.setBackgroundColor(resources.getColor(R.color.dn_white))
            settingIoc.backgroundTintList = ColorStateList.valueOf(Color.WHITE)

        }

        darkmodeSwitch.isChecked = LocalStorage(requireActivity()).getNightMode(kNightMode) != -1

        darkmodeSwitch.setOnCheckedChangeListener {
                _, isChecked -> darkmodeSwitch.isChecked = isChecked
            if (isChecked) {
                LocalStorage(requireActivity()).putNightMode(kNightMode, 1)
            } else {
                LocalStorage(requireActivity()).putNightMode(kNightMode, -1)
            }
            IntentEvent.onSplace(requireActivity())
        }

        bookmarkTxt.setOnClickListener {
            IntentEvent.onMore_Bookmark(requireActivity())
        }

    }


}