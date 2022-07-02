package ns.kk.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import data.BookMarkDataSourceImp
import ns.kk.newsdxio.BookMarkEntity
import ns.kk.newsdxio.DatabaseDriverFactory
import ns.kk.newsdxio.MyAppDb


object ConstantMethod {

     fun shareTextUrl(activity: Activity, title: String, url: String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, title)
        share.putExtra(Intent.EXTRA_TEXT, url)
        activity.startActivity(Intent.createChooser(share, "Share link!"))
    }

    fun getBookmarkArticleList(context: Context): String {
        val dataSource = BookMarkDataSourceImp(MyAppDb.invoke(DatabaseDriverFactory(context).createDriver()))
        val bookmarkCount = dataSource.getBookmarkAll()
        var articelIdList = ""
        for ((pos, item) in bookmarkCount.withIndex()) {
            if (pos == 0) {
                articelIdList = item.article_id
            } else {
                articelIdList = articelIdList + "," + item.article_id
            }
        }
        return articelIdList
    }
}