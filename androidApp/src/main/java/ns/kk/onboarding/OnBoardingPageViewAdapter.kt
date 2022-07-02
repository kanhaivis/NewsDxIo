package ns.kk.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import model.onboarding.DATA
import ns.kk.newsdxio.android.R

class OnBoardingPageViewAdapter(var context: Context,
                                var itemList : List<DATA>) : RecyclerView.Adapter<MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return  MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_row, parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val item = itemList[position]
        holder.ontitle.text = item.title
        Picasso.with(context)
            .load(item.image_url)
            .into(holder.onThumb)

    }

    override fun getItemCount(): Int {
       return  itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onUpdateData(_itemList : List<DATA>){
        itemList = _itemList
        notifyDataSetChanged()
    }

}

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var onThumb = itemView.findViewById<ImageView>(R.id.onboarding_row_thumb)
        var ontitle = itemView.findViewById<TextView>(R.id.onboarding_row_title)
}