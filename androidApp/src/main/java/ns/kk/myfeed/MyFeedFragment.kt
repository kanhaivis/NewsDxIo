package ns.kk.myfeed

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ns.kk.baseclass.BaseFragment
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.android.R

class MyFeedFragment : BaseFragment() {
    private val mainScope = MainScope()
    override val setLayout: Int
        get() = R.layout.myfeed_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.myfeed_recyclerview)
        val myFeedAdapter = MyFeedAdapter(arrayListOf())
        recycler.adapter = myFeedAdapter

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recycler.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )

        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getSection()
            }.onSuccess {
                myFeedAdapter.onUpdateData(it.DATA)
            }.onFailure {
//                tv.text = "Error: ${it.localizedMessage}"
            }
        }

    }


}