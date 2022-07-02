package ns.kk.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import ns.kk.artilce_detail.ArtilceDeatilsActivity
import ns.kk.bookmark.BookMakrActivity
import ns.kk.newsdxio.NewsDxIoMainScreen
import ns.kk.onboarding.OnBoardingActivity
import ns.kk.profile.ProfileActivity
import ns.kk.profile.SignInFragment
import ns.kk.splace.SplaceActivity
import ns.kk.subscription.SubscriptionActivity

object IntentEvent {

    fun onArticleRow_ArticelDeatils(activity: Activity, articleId: String){
        var bundle = Bundle()
        bundle.putString("article_id",articleId)
        val intent = Intent(activity, ArtilceDeatilsActivity::class.java)
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }

    fun onTopBar_Profile(activity: Activity){
        val intent = Intent(activity, ProfileActivity::class.java)
        activity.startActivity(intent)
    }

    fun onMore_Bookmark(activity: Activity){
        val intent = Intent(activity, BookMakrActivity::class.java)
        activity.startActivity(intent)
    }

    fun onOnBoarding(activity: Activity){
        val intent = Intent(activity, OnBoardingActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun goNewsDxInMain(activity: Activity){
        val intent = Intent(activity, NewsDxIoMainScreen::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun onSplace(activity: Activity){
        val intent = Intent(activity, SplaceActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun onSubscription(activity: Activity) {
        val intent = Intent(activity, SubscriptionActivity::class.java)
        activity.startActivity(intent)
    }
}
