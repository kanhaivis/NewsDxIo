package ns.kk

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import data.BookMarkDataSourceImp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ns.kk.network.ServiceApi
import ns.kk.newsdxio.DatabaseDriverFactory
import ns.kk.newsdxio.MyAppDb
import ns.kk.newsdxio.android.R

class TestApiCheck : AppCompatActivity() {
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testapicall_activity)

        val txt = findViewById<TextView>(R.id.test_ap_call)
        mainScope.launch {
            kotlin.runCatching {
                ServiceApi().getSectionArticleeeee("4","1")
            }.onSuccess {
                txt.text = it
            }.onFailure {
                txt.text = "Error: ${it.localizedMessage}"
            }
        }


    }
}