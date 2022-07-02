package ns.kk.bookmark

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import data.BookMarkDataSourceImp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import model.home.ARTICLES
import ns.kk.baseclass.BaseActivity
import ns.kk.callback.OnBookmarkEvent
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.DatabaseDriverFactory
import ns.kk.newsdxio.MyAppDb
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantMethod.getBookmarkArticleList

class BookMakrActivity : BaseActivity() , OnBookmarkEvent {

    private val mainScope = MainScope()

    lateinit var bookmarkAdapter : BookmarkAdapter

    private var articleList : ArrayList<ARTICLES> = arrayListOf()

    override val setLayout: Int
        get() = R.layout.bookmakr_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataSource = BookMarkDataSourceImp(MyAppDb.invoke(DatabaseDriverFactory(this).createDriver()))

        onTopBarShowDetails()

        val bookmarkRecyclerview = findViewById<RecyclerView>(R.id.bookmark_recyclerview)
        bookmarkAdapter = BookmarkAdapter(this, this, dataSource, listOf())
        bookmarkRecyclerview.adapter = bookmarkAdapter

        mainScope.launch {
            val articleList =getBookmarkArticleList(this@BookMakrActivity)
            kotlin.runCatching {
                ServiceApi().getArticleById(articleList)
            }.onSuccess {
                this@BookMakrActivity.articleList = it
                bookmarkAdapter.undateList(it)
            }.onFailure {
                println("Error: ${it.localizedMessage}")
            }
        }
    }


    override fun onEvent(index: Int) {
        articleList.removeAt(index)
        bookmarkAdapter.undateList(articleList)
    }

}