package com.rao.mercadolibre.ui.publication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.rao.mercadolibre.R
import com.rao.mercadolibre.adapter.PictureAdapter
import com.rao.mercadolibre.common.Constants
import com.rao.mercadolibre.retrofit.models.Article
import kotlinx.android.synthetic.main.activity_detail_publication.*
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
        val price = format.format(detailPublication.price.roundToInt())
        price_product.text = "$ ${price.replace(",00", "").replace("ARS", "")}"
        setCondition(detailPublication.condition, detailPublication.sold_quantity)
        setDescription(detailPublication.id)
        setCarousel(detailPublication.id)
    }

    private fun setCondition(conditionPublication: String, soldQuantity: Int) {
        if (conditionPublication == "new") {
            condition.text = "Nuevo - $soldQuantity vendidos"
        } else {
            condition.text = "$soldQuantity vendidos"
        }
    }

    private fun setDescription(idPublication: String) {
        detailPublicationViewModel.detailProduct.observe(this, Observer {
            if (!it.isEmpty()) {
                description_product.text = it[0].plain_text
            } else {
                Toast.makeText(this, "No se encontro descripcion del producto.", Toast.LENGTH_LONG)
            }
        })

        if (detailPublicationViewModel.detailProduct.value == null) {
            detailPublicationViewModel.getDetailProduct(idPublication) { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG)
            }
        }
    }

    private fun setCarousel(idPublication: String) {

        detailPublicationViewModel.item.observe(this, Observer {
            if (!it.pictures.isEmpty()) {
                val pictureAdapter = PictureAdapter(it.pictures)
                image_product.adapter = pictureAdapter
            } else {
                Toast.makeText(this, "No se encontraron imagenes del producto.", Toast.LENGTH_LONG)
            }
        })

        if (detailPublicationViewModel.item.value == null) {
            detailPublicationViewModel.getItems(idPublication) { message ->
                Toast.makeText(this, message, Toast.LENGTH_LONG)
            }
        }
    }

//endregion

}

