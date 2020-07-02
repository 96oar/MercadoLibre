package com.rao.mercadolibre.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.rao.mercadolibre.ui.publication.DetailPublicationActivity
import com.squareup.picasso.Picasso

class PictureAdapter(val imageUrls: ArrayList<com.rao.mercadolibre.retrofit.models.Picture>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        Picasso.get()
            .load(imageUrls[position].secure_url)
            .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}