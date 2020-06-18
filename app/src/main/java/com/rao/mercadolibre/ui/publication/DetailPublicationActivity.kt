package com.rao.mercadolibre.ui.publication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.rao.mercadolibre.R
import com.rao.mercadolibre.adapter.PictureAdapter
import com.rao.mercadolibre.common.Constants
import com.rao.mercadolibre.retrofit.models.Article
import kotlinx.android.synthetic.main.activity_detail_publication.*
import kotlinx.android.synthetic.main.publication.view.*
import kotlinx.android.synthetic.main.toolbar_no_search.*
import java.text.NumberFormat
import java.util.*
import kotlin.math.roundToInt

class DetailPublicationActivity : AppCompatActivity() {
    lateinit var detailPublicationViewModel: DetailPublicationViewModel

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

    //region private functions

    private fun populatePublication(detailPublication: Article) {
        title_product.text = detailPublication.title
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance(detailPublication.currency_id)
        val price = format.format(detailPublication.price.roundToInt() as Int)
        price_product.text ="$ ${price.replace(",00","").replace("ARS","")}"

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

        detailPublicationViewModel.item.observe(this, Observer {
            val pictureAdapter = PictureAdapter(this, it.pictures)
            image_product.adapter = pictureAdapter
        })

        if (detailPublicationViewModel.item.value == null) {
            detailPublicationViewModel.getItems(detailPublication.id)
        }
    }

//endregion

}

