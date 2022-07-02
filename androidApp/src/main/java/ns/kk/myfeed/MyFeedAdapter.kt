package ns.kk.myfeed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.section.DATA
import ns.kk.newsdxio.android.R

class MyFeedAdapter(var sectionList : ArrayList<DATA>) : RecyclerView.Adapter<MyFeedHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFeedHolder {
        return MyFeedHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_myfeed, parent, false))
    }

    override fun onBindViewHolder(holder: MyFeedHolder, position: Int) {
            holder.title.text = sectionList[position].SECTION_NAME
    }

    override fun getItemCount(): Int {
        return sectionList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onUpdateData(_sectionList : ArrayList<DATA>){
        sectionList = _sectionList
        notifyDataSetChanged()
    }
}

class MyFeedHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    var title = viewItem.findViewById<TextView>(R.id.row_myfeed_title)
}