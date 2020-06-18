package com.rao.mercadolibre.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.rao.mercadolibre.R.layout
import com.rao.mercadolibre.adapter.ProductAdapter.ViewHolder
import com.rao.mercadolibre.common.Constants
import com.rao.mercadolibre.retrofit.models.Article
import com.rao.mercadolibre.ui.publication.DetailPublicationActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.publication.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

//val showItemDetails:(article: Article) -> Unit
class ProductAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var dataList = listOf<Article>()
    fun setDataList(data: List<Article>) {
        dataList = data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout.publication, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val publication = dataList[position]
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance(publication.currency_id)
        val price = format.format(publication.price.roundToInt() as Int)
        holder.itemView.title.text = publication.title
        holder.itemView.price.text ="$ ${price.replace(",00","").replace("ARS","")}"

        Picasso.get()
            .load(publication.thumbnail.replace("http", "https"))
            .into(holder.itemView.logo_product)

        if(publication.listing_type_id == "gold_special" ){
            holder.itemView.ship_normally.visibility = View.VISIBLE
        }

        if (publication.shipping.free_shipping) {
            holder.itemView.free_shipping.visibility = View.VISIBLE
        }else{
            holder.itemView.free_shipping.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context,DetailPublicationActivity::class.java).apply {
                putExtra(Constants.PUBLICATION_PARAM, Gson().toJson(publication))
                it.context.startActivity(this)
            }
        }

    }
}