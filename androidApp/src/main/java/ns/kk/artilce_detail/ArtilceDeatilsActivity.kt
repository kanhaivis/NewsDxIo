package ns.kk.artilce_detail

import ConstantItem.kNightMode
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import data.BookMarkDataSourceImp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import model.articledetails.DATA
import ns.kk.baseclass.BaseActivity
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.DatabaseDriverFactory
import ns.kk.newsdxio.LocalStorage
import ns.kk.newsdxio.MyAppDb
import ns.kk.newsdxio.android.R
import ns.kk.utils.ConstantMethod
import ns.kk.utils.ImagePath
import ns.kk.utils.IntentEvent
import java.util.*

class ArtilceDeatilsActivity : BaseActivity() ,TextToSpeech.OnInitListener{


    private val mainScope = MainScope()

    lateinit var thumb : RoundedImageView
    lateinit var title : TextView
    lateinit var datetime : TextView
    lateinit var leadText : TextView
    lateinit var description : TextView
    lateinit var speakericon : ImageView
    lateinit var bookmarkIcon : ImageView
    lateinit var unbookmarkIcon : ImageView
    lateinit var shareIcon : ImageView
    lateinit var premiumLinearLayout: LinearLayout
    lateinit var adManager : AdManagerAdView

    private var tts: TextToSpeech? = null

    private var articleTitleDesc : String = ""


    override val setLayout: Int
        get() = R.layout.artilce_details_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataSource = BookMarkDataSourceImp(MyAppDb.invoke(DatabaseDriverFactory(applicationContext).createDriver()))

        val articleId = intent.extras?.get("article_id").toString()
        thumb = findViewById(R.id.article_details_thumb)
        title = findViewById(R.id.article_details_title)
        datetime = findViewById(R.id.article_details_datetime)
        leadText = findViewById(R.id.article_details_lead_txt)
        description = findViewById(R.id.article_details_description)
        speakericon = findViewById(R.id.speaker_icon)
        bookmarkIcon = findViewById(R.id.article_row_bookmark_add)
        unbookmarkIcon = findViewById(R.id.article_row_bookmark_remove)
        shareIcon = findViewById(R.id.article_share)
        premiumLinearLayout = findViewById(R.id.preimum_view)
        adManager = findViewById(R.id.adManagerAdView)


        tts = TextToSpeech(this, this)

        onTopBarShowDetails()

        val adRequest = AdManagerAdRequest.Builder().build()
        adManager.loadAd(adRequest)

        if (LocalStorage(this).getNightMode(kNightMode) != -1) {
            bookmarkIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            unbookmarkIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            speakericon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            shareIcon.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

        mainScope.launch {
            val item = dataSource.getBookMarsArticleById(articleId)
            if (item != null) {
                bookmarkIcon.visibility = View.VISIBLE
                unbookmarkIcon.visibility = View.INVISIBLE
            }  else {
                unbookmarkIcon.visibility = View.VISIBLE
                bookmarkIcon.visibility = View.INVISIBLE
            }
        }

        bookmarkIcon.setOnClickListener {
            mainScope.launch{
                dataSource.deleteBookmarkById(articleId)
                bookmarkIcon.visibility = View.INVISIBLE
                unbookmarkIcon.visibility = View.VISIBLE
            }
        }

        unbookmarkIcon.setOnClickListener {
            mainScope.launch{
                dataSource.insertBookmarkArticle(articleId.toLong(),articleId)
                unbookmarkIcon.visibility = View.INVISIBLE
                bookmarkIcon.visibility = View.VISIBLE
            }
        }

        speakericon.setOnClickListener {
            if (!articleTitleDesc.isEmpty()) {
                tts!!.speak(articleTitleDesc, TextToSpeech.QUEUE_FLUSH, null,"")
            }

        }

        findViewById<TextView>(R.id.planView_Event).setOnClickListener {
            IntentEvent.onSubscription(this)
        }


        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getArticleDetails(articleId)
            }.onSuccess {
                onArtilceDeatils(it.DATA)
            }.onFailure {
                println("Error: ${it.localizedMessage}")
//                txt.text = "Error: ${it.localizedMessage}"
            }
        }

        findViewById<ImageView>(R.id.article_share).setOnClickListener {
            ConstantMethod.shareTextUrl(this,"","")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onArtilceDeatils(data: DATA) {

        if (data.premium) {
            premiumLinearLayout.visibility = View.VISIBLE
        }
        articleTitleDesc = data.TITLE + data.DESCRIPTION

        datetime.text = data.PUBLISH_DATE

        data.TITLE?.let {
            title.text = "        "+it.replace("\n", "")
        }

        data.LEAD_TEXT?.let {
            leadText.text = it
        }

        data.DESCRIPTION?.let {
            description.text = it
        }

        if (data.IMAGES?.size !=0) {
            thumb.visibility = View.VISIBLE
            data.IMAGES?.get(0)?.also {
                Picasso.with(this)
                    .load(ImagePath.getBigThumbImageUlr(it.IMAGE_ID))
                    .into(thumb)
            }
        } else {
            thumb.visibility = View.GONE
        }

    }




    /*  if (it.IMAGE?.size !=0) {
          item.IMAGES?.get(0)?.also {
              Picasso.with(activity)
                  .load(ImagePath.getThumbImageUlr(it.IMAGE_ID))
                  .into(holder.thumb)
          }
      }*/

    public override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
//                buttonSpeak!!.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

}