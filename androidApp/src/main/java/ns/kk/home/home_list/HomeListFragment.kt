package ns.kk.home.home_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import data.BookMarkDataSourceImp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ns.kk.baseclass.BaseFragment
import ns.kk.home.home_list.adapter.HomeListAdapter
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.DatabaseDriverFactory
import ns.kk.newsdxio.MyAppDb
import ns.kk.newsdxio.android.R

class HomeListFragment(private val pos: Int): BaseFragment() {
    private val mainScope = MainScope()

    lateinit var recyclerview : RecyclerView
    lateinit var homeAdapter : HomeListAdapter

    override val setLayout: Int
        get() = R.layout.home_article_list_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSource = BookMarkDataSourceImp(MyAppDb.invoke(DatabaseDriverFactory(requireActivity()).createDriver()))

        recyclerview = view.findViewById(R.id.home_recyclerview)
        homeAdapter = HomeListAdapter(requireActivity(),dataSource,listOf())
        recyclerview.adapter = homeAdapter

        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getHome()
            }.onSuccess {
                it.let {
                    it?.let { articleList -> homeAdapter.onUpdateArticle(articleList) }
                }
//                it..ARTICLES?.let { articleList -> homeAdapter.onUpdateArticle(articleList) }
            }.onFailure {
//                tv.text = "Error: ${it.localizedMessage}"
            }
        }



    }
}