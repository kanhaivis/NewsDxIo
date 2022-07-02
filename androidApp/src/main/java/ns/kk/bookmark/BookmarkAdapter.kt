package ns.kk.bookmark

import ConstantItem.kNightMode
import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import data.BookMarkDataSourceImp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import model.home.ARTICLES
import ns.kk.callback.OnBookmarkEvent
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantMethod
import ns.kk.utils.DateTimeMethod
import ns.kk.utils.ImagePath
import ns.kk.utils.IntentEvent

class BookmarkAdapter(private val activity: Activity,
                      private val onBookmarkEvent: OnBookmarkEvent,
                      private val dataSourceImp: BookMarkDataSourceImp,
                      private var articelList: List<ARTICLES>)
    : RecyclerView.Adapter<BookmarkHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        return  BookmarkHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_article,parent,false))
    }

    override fun onBindViewHolder(articleHolder: BookmarkHolder, position: Int) {

        val articles = articelList[position]
         articles.TITLE?.let {
             articleHolder.title.text = it.replace("\n", "")
        }

        articleHolder.datetime.text = articles.PUBLISH_DATE_GMT_MILLIS?.let {
            DateTimeMethod.getMinDay(
                it
            )
        }

        if (LocalStorage(activity).getNightMode(kNightMode) != -1) {
            articleHolder.bookmarkIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            articleHolder.unbookmarkIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            articleHolder.shareIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

        MainScope().launch {
            val item = articles.ARTICLE_ID?.let {
                dataSourceImp.getBookMarsArticleById(
                    it
                )
            }
            if (item != null) {
                articleHolder.bookmarkIcon.visibility = View.VISIBLE
                articleHolder.unbookmarkIcon.visibility = View.INVISIBLE
            }  else {
                articleHolder.unbookmarkIcon.visibility = View.VISIBLE
                articleHolder.bookmarkIcon.visibility = View.INVISIBLE
            }
        }

        articleHolder.bookmarkIcon.setOnClickListener {
            MainScope().launch {
                dataSourceImp.deleteBookmarkById(articles.ARTICLE_ID.toString())
                articleHolder.unbookmarkIcon.visibility = View.VISIBLE
                articleHolder.bookmarkIcon.visibility = View.INVISIBLE
                onBookmarkEvent.onEvent(position)
            }
        }



        if (articles.IMAGES?.size !=0) {
            articles.IMAGES?.get(0)?.also {
                Picasso.with(activity)
                    .load(ImagePath.getThumbImageUlr(it.IMAGE_ID))
                    .into(articleHolder.thumb)
            }
        }

        articleHolder.thumb.setOnClickListener {
            articles.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }
        }
        articleHolder.title.setOnClickListener {
            articles.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }
        }
        articleHolder.shareIcon.setOnClickListener {
            articles.TITLE?.let { it1 -> articles.LINK?.let { it2 ->
                ConstantMethod.shareTextUrl(activity, it1,
                    it2
                )
            } }
        }
    }

    override fun getItemCount(): Int {
       return articelList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun undateList(_articelList: List<ARTICLES>) {
        articelList = _articelList
        notifyDataSetChanged()
    }
}

class BookmarkHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    var itemViews = viewItem
    var thumb = viewItem.findViewById<RoundedImageView>(R.id.article_row_thumb)
    var shareIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_share)
    var bookmarkIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_add)
    var unbookmarkIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_remove)
    var title = viewItem.findViewById<TextView>(R.id.article_row_title)
    var datetime = viewItem.findViewById<TextView>(R.id.article_row_datetime)
}