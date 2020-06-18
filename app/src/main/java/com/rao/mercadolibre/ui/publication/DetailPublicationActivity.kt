package com.rao.mercadolibre.ui.publication

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.rao.mercadolibre.R
import com.rao.mercadolibre.common.Constants
import com.rao.mercadolibre.retrofit.models.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_publication.*
import kotlinx.android.synthetic.main.publication.view.*
import kotlinx.android.synthetic.main.toolbar_no_search.*

class DetailPublicationActivity : AppCompatActivity() {
    lateinit var detailPublicationViewModel: DetailPublicationViewModel
    lateinit var listImage : ArrayList<ImageView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_publication)
        ic_back.setOnClickListener {
            onBackPressed()
        }

        detailPublicationViewModel = ViewModelProvider(this).get(DetailPublicationViewModel::class.java)

        if (intent.extras!!.containsKey(Constants.PUBLICATION_PARAM)) {
            val detailPublication: Article =
                Gson().fromJson(intent.extras!!.getString(Constants.PUBLICATION_PARAM), Article::class.java)
            populatePublication(detailPublication)
        }

    }


    private fun populatePublication(detailPublication: Article) {
        title_product.text = detailPublication.title

        if (detailPublication.condition == "new") {
            condition.text = "Nuevo - ${detailPublication.sold_quantity.toString()} vendidos"
        } else {
            condition.text = "${detailPublication.sold_quantity.toString()} vendidos"
        }

        detailPublicationViewModel.detailProduct.observe(this, Observer {
            description_product.text = it[0].plain_text
        })

        if (detailPublicationViewModel.detailProduct.value == null) {
            detailPublicationViewModel.getDetailProduct(detailPublication.id)
        }

        Picasso.get()
            .load(detailPublication.thumbnail.replace("http", "https"))
            .into(image_product)
    }


}