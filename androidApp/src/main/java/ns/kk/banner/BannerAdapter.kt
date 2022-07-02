package ns.kk.banner

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import model.home.ARTICLES
import ns.kk.newsdxio.android.R
import ns.kk.utils.ImagePath
import ns.kk.utils.IntentEvent

class BannerAdapter(var activity: Activity,
                           var articleList : List<ARTICLES>)
    : RecyclerView.Adapter<BannerViewMyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewMyHolder {
        return  BannerViewMyHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_row_banner_view, parent, false))
    }

    override fun onBindViewHolder(holder: BannerViewMyHolder, position: Int) {

        val item = articleList[position]
        holder.title.text =  item.TITLE
        Picasso.with(activity)
            .load(ImagePath.getThumbImageUlr(item.THUMBNAIL.toString()))
            .into(holder.thumb)
        holder.thumb.setOnClickListener {
            item.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }

        }
    }

    override fun getItemCount(): Int {
       return articleList.size
    }
}

class BannerViewMyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var itemViews = itemView
    var thumb = itemView.findViewById<RoundedImageView>(R.id.home_row_banner_view_thumb)
    var title = itemView.findViewById<TextView>(R.id.home_row_banner_view_title)
}