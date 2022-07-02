package ns.kk.section.adapter

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
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantMethod
import ns.kk.utils.DateTimeMethod
import ns.kk.utils.ImagePath
import ns.kk.utils.IntentEvent

class SectionArticleAdapter(private val activity: Activity,
                            private val dataSourceImp: BookMarkDataSourceImp,
                            var articleList: List<ARTICLES>): RecyclerView.Adapter<SectionArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionArticleHolder {
        return  SectionArticleHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_article, parent, false))
    }

    override fun onBindViewHolder(holder: SectionArticleHolder, position: Int) {
        val item = articleList[position]

        item.TITLE?.let {
            holder.title.text = it.replace("\n","")
        }

        holder.datetime.text = item.PUBLISH_DATE_GMT_MILLIS?.let {
            DateTimeMethod.getMinDay(
                it
            )
        }

        if (LocalStorage(activity).getNightMode(kNightMode) != -1) {
            holder.bookmarkIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            holder.unbookmarkIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            holder.shareIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }


        MainScope().launch {
            val item = item.ARTICLE_ID?.let {
                dataSourceImp.getBookMarsArticleById(
                    it
                )
            }
            if (item != null) {
                holder.bookmarkIcon.visibility = View.VISIBLE
                holder.unbookmarkIcon.visibility = View.INVISIBLE
            }  else {
                holder.unbookmarkIcon.visibility = View.VISIBLE
                holder.bookmarkIcon.visibility = View.INVISIBLE
            }
        }

        holder.bookmarkIcon.setOnClickListener {
            MainScope().launch {
                dataSourceImp.deleteBookmarkById(item.ARTICLE_ID.toString())
                holder.unbookmarkIcon.visibility = View.VISIBLE
                holder.bookmarkIcon.visibility = View.INVISIBLE
            }
        }

        holder.unbookmarkIcon.setOnClickListener {
            MainScope().launch {
                dataSourceImp.insertBookmarkArticle((item.ARTICLE_ID)?.toLong()
                    ?: 0,item.ARTICLE_ID.toString())
                holder.bookmarkIcon.visibility = View.VISIBLE
                holder.unbookmarkIcon.visibility = View.INVISIBLE
            }
        }

        if (item.IMAGES?.size !=0) {
            item.IMAGES?.get(0)?.also {
                Picasso.with(activity)
                    .load(ImagePath.getThumbImageUlr(it.IMAGE_ID))
                    .into(holder.thumb)
            }
        }

        holder.thumb.setOnClickListener {
            item.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }
        }
        holder.title.setOnClickListener {
            item.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }
        }
        holder.shareIcon.setOnClickListener {
            item.TITLE?.let { it1 -> item.LINK?.let { it2 ->
                ConstantMethod.shareTextUrl(activity, it1,
                    it2
                )
            } }
        }
    }

    override fun getItemCount(): Int {
      return articleList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onUpdateArticle(_artilceList: List<ARTICLES>){
        articleList = _artilceList
        notifyDataSetChanged()
    }
}

class SectionArticleHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    var itemViews = viewItem
    var thumb = viewItem.findViewById<RoundedImageView>(R.id.article_row_thumb)
    var shareIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_share)
    var bookmarkIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_add)
    var unbookmarkIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_remove)
    var title = viewItem.findViewById<TextView>(R.id.article_row_title)
    var datetime = viewItem.findViewById<TextView>(R.id.article_row_datetime)
}