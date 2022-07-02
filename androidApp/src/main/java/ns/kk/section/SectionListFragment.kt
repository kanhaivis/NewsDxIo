package ns.kk.section

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import data.BookMarkDataSourceImp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ns.kk.baseclass.BaseFragment
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.DatabaseDriverFactory
import ns.kk.newsdxio.MyAppDb
import ns.kk.newsdxio.android.R
import ns.kk.section.adapter.SectionArticleAdapter

class SectionListFragment(var sectionId: String): BaseFragment() {
    private val mainScope = MainScope()
    override val setLayout: Int
        get() = R.layout.section_article_list_fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSource = BookMarkDataSourceImp(MyAppDb.invoke(DatabaseDriverFactory(requireActivity()).createDriver()))


        val recyclerview = view.findViewById<RecyclerView>(R.id.section_article_recyclerview)
        val articleAdapter = SectionArticleAdapter(requireActivity(),dataSource, listOf())
        recyclerview.adapter = articleAdapter

        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getSectionArticle(sectionId)
            }.onSuccess {
                articleAdapter.onUpdateArticle(it.DATA.ARTICLES)
            }.onFailure {
                print("Error: ${it.localizedMessage}")
//                tv.text = "Error: ${it.localizedMessage}"
            }
        }

    }
}