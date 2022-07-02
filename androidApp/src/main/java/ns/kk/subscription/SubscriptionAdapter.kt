package ns.kk.subscription

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ns.kk.newsdxio.android.R


class SubscriptionAdapter(
    private val activity: Activity,
    private val subscriptionLisr: ArrayList<Subsc>
) : RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_subscription, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title1.text = subscriptionLisr[position].detail
        holder.title2.text = subscriptionLisr[position].duration
        holder.title3.text = subscriptionLisr[position].price
    }

    override fun getItemCount(): Int {
       return subscriptionLisr.size
    }
}

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title1 = itemView.findViewById<TextView>(R.id.product1_txt)
    val title2 = itemView.findViewById<TextView>(R.id.product2_txt)
    val title3 = itemView.findViewById<TextView>(R.id.product3_txt)

}