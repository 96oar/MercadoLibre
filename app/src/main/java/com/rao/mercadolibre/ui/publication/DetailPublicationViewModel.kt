package com.rao.mercadolibre.ui.publication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.R
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Detail

class DetailPublicationViewModel : ViewModel() {
    private val meLiRepository: MeLiRepository = MeLiRepository()

    var detailProduct: MutableLiveData<ArrayList<Detail>> = MutableLiveData<ArrayList<Detail>>()
    var item: MutableLiveData<Detail> = MutableLiveData<Detail>()
    var message: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getDetailProduct(idProduct: String) {
        meLiRepository.responseDetail(idProduct,
            { _, _ ->
                message.postValue(R.string.error_connection)
            },
            { _, response ->
                if (response.isSuccessful) {
                    if (response.body()!!.isNotEmpty()) {
                        detailProduct.postValue(response.body())
                    } else {
                        message.postValue(R.string.no_detail_product)
                    }
                } else {
                    message.postValue(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

    fun getItems(idItem: String) {
        meLiRepository.responseGetItem(idItem,
            { _, _ ->
                message.postValue(R.string.error_connection)
            },
            { _, response ->
                if (response.isSuccessful) {
                    if (response.body()!!.pictures.isNotEmpty()) {
                        item.postValue(response.body()!!)
                    } else {
                        message.postValue(R.string.no_images)
                    }
                } else {
                    message.postValue(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

}