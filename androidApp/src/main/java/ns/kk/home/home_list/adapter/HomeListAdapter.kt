package ns.kk.home.home_list.adapter

import ConstantItem.kNightMode
import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import data.BookMarkDataSourceImp
import kotlinx.coroutines.*
import model.home.HomeModles
import ns.kk.banner.BannerAdapter
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantMethod
import ns.kk.utils.DateTimeMethod.getMinDay
import ns.kk.utils.ImagePath
import ns.kk.utils.IntentEvent
import ns.kk.widget.WidgetTypeOneAdapter


class HomeListAdapter(val activity: Activity,
                      val dataSourceImp: BookMarkDataSourceImp,
                      var artilceList: List<HomeModles>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            1 -> return  MyHolderHomeBanner(LayoutInflater.from(parent.context).inflate(R.layout.home_row_banner, parent, false))
            2 -> return  MyHolderHomeWidget(LayoutInflater.from(parent.context).inflate(R.layout.home_row_widget, parent, false))
            3 -> return  MyHolderHomeAds(LayoutInflater.from(parent.context).inflate(R.layout.row_ad, parent, false))
            else -> return  MyHolderHomeArticle(LayoutInflater.from(parent.context).inflate(R.layout.row_article, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val homeItems = artilceList[position]
        when(getItemViewType(position)) {
            0 -> {
                val articleHolder = holder as MyHolderHomeArticle
                homeItems.articles.TITLE?.let {
                    articleHolder.title.text = it.replace("\n","")
                }

                articleHolder.datetime.text = homeItems.articles.PUBLISH_DATE_GMT_MILLIS?.let {
                    getMinDay(
                        it
                    )
                }

                if (LocalStorage(activity).getNightMode(kNightMode) != -1) {
                    articleHolder.bookmarkIcon.backgroundTintList =
                        ColorStateList.valueOf(Color.WHITE)
                    articleHolder.unbookmarkIcon.backgroundTintList =
                        ColorStateList.valueOf(Color.WHITE)
                    articleHolder.shareIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                }

                if (homeItems.articles.IMAGES?.size !=0) {
                    homeItems.articles.IMAGES?.get(0)?.also {
                        Picasso.with(activity)
                            .load(ImagePath.getThumbImageUlr(it.IMAGE_ID))
                            .into(articleHolder.thumb)
                    }
                }

                articleHolder.thumb.setOnClickListener {
                    homeItems.articles.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }
                }
                articleHolder.title.setOnClickListener {
                    homeItems.articles.ARTICLE_ID?.let { articleId -> IntentEvent.onArticleRow_ArticelDeatils(activity, articleId) }
                }
                articleHolder.shareIcon.setOnClickListener {
                    homeItems.articles.TITLE?.let { it1 -> homeItems.articles.LINK?.let { it2 ->
                        ConstantMethod.shareTextUrl(activity, it1,
                            it2
                        )
                    } }
                }

                MainScope().launch {
                    val item = homeItems.articles.ARTICLE_ID?.let {
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
                        dataSourceImp.deleteBookmarkById(homeItems.articles.ARTICLE_ID.toString())
                        articleHolder.unbookmarkIcon.visibility = View.VISIBLE
                        articleHolder.bookmarkIcon.visibility = View.INVISIBLE
                    }
                }

                articleHolder.unbookmarkIcon.setOnClickListener {
                    MainScope().launch {
                        dataSourceImp.insertBookmarkArticle((homeItems.articles.ARTICLE_ID)?.toLong()
                            ?: 0,homeItems.articles.ARTICLE_ID.toString())
                        articleHolder.bookmarkIcon.visibility = View.VISIBLE
                        articleHolder.unbookmarkIcon.visibility = View.INVISIBLE
                    }
                }

            } // Article
            1 -> {
                val bannerHolder = holder as MyHolderHomeBanner

                val adapterBanner = BannerAdapter(activity, homeItems.articleslist)
                bannerHolder.viewpager.adapter = adapterBanner
                bannerHolder.viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    // This method is triggered when there is any scrolling activity for the current page
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }

                    // triggered when you select a new page
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }

                    // triggered when there is
                    // scroll state will be changed
                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                    }
                })
            } // Banner
            2 -> {
                val widgetHolder = holder as MyHolderHomeWidget
                widgetHolder.title.text = homeItems.articleslist[0].SECTION_NAME

                widgetHolder.recyclerview.setLayoutManager(
                    LinearLayoutManager(
                        activity,
                        LinearLayoutManager.HORIZONTAL,
                        true
                    )
                )
                val adapter = WidgetTypeOneAdapter(activity,homeItems.articleslist)
                widgetHolder.recyclerview.adapter = adapter

            } // Widget
            3 -> {
                val adHolder = holder as MyHolderHomeAds
                val adRequest = AdManagerAdRequest.Builder().build()
                adHolder.adManager.loadAd(adRequest)
            } // ad
        }

    }

    override fun getItemCount(): Int {
        return artilceList.size
    }

    override fun getItemViewType(position: Int): Int {

        when(artilceList[position].type) {
            "BANNER" -> return 1
            "WIDGET" -> return 2
            "ADS" -> return 3
            else -> return 0
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onUpdateArticle(_artilceList: List<HomeModles>){
        artilceList = _artilceList
        notifyDataSetChanged()
    }


}

class MyHolderHomeBanner(viewItem: View) : RecyclerView.ViewHolder(viewItem){
    var itemViews = viewItem
    var viewpager = viewItem.findViewById<ViewPager2>(R.id.home_row_banner_viewpager)
}

class MyHolderHomeArticle(viewItem: View) : RecyclerView.ViewHolder(viewItem){
    var itemViews = viewItem
    var thumb = viewItem.findViewById<RoundedImageView>(R.id.article_row_thumb)
    var shareIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_share)
    var bookmarkIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_add)
    var unbookmarkIcon = viewItem.findViewById<ImageView>(R.id.article_row_bookmark_remove)
    var title = viewItem.findViewById<TextView>(R.id.article_row_title)
    var datetime = viewItem.findViewById<TextView>(R.id.article_row_datetime)
}

class MyHolderHomeWidget(viewItem: View) : RecyclerView.ViewHolder(viewItem){
    var itemViews = viewItem
    var recyclerview = viewItem.findViewById<RecyclerView>(R.id.home_row_widget_recyclerview)
    var title = viewItem.findViewById<TextView>(R.id.home_row_widget_name_txt)
}

class MyHolderHomeAds(viewItem: View) : RecyclerView.ViewHolder(viewItem){
    var itemViews = viewItem
    var adManager = viewItem.findViewById<AdManagerAdView>(R.id.adManagerAdView)
}