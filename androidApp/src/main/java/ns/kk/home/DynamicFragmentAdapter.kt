package ns.kk.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import androidx.fragment.app.FragmentStatePagerAdapter
import model.section.DATA
import ns.kk.home.home_list.HomeListFragment
import ns.kk.section.SectionListFragment


class DynamicFragmentAdapter internal constructor(
    fm: FragmentManager?,
    private val mNumOfTabs: Int,
    private val sectionList: List<DATA>
) :
    FragmentStatePagerAdapter(fm!!) {
    // get the current item with position number
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return  HomeListFragment(position)
        } else {
            sectionList[position].ID.let {
                return SectionListFragment(it.toString())
            }

        }
    }

    // get total number of tabs
    override fun getCount(): Int {
        return mNumOfTabs
    }
}