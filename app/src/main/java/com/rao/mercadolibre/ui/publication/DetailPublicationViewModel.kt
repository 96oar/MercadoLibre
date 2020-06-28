package com.rao.mercadolibre.ui.publication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.R
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Detail

class DetailPublicationViewModel : ViewModel() {
    private val meLiRepository: MeLiRepository = MeLiRepository()

    var detailProduct = MutableLiveData<ArrayList<Detail>>()
    var item = MutableLiveData<Detail>()
    var message = MutableLiveData<String>()

    fun getDetailProduct(idProduct: String) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
            meLiRepository.responseDetail(
                idProduct,
                { _, t ->
                    message.postValue(R.string.error_connection.toString())
                },
                { _, response ->
                    if (response.isSuccessful) {
                        detailProduct.postValue(response.body())
                    } else {
                        message.postValue(CodeError.evaluateResponseCode(response.code()))
                    }
                })
    }

    fun getItems(idItem: String) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseGetItem(
            idItem,
            { _, t ->
                message.postValue(R.string.error_connection.toString())
            },
            { _, response ->
                if (response.isSuccessful) {
                    item.postValue(response.body())
                } else {
                    message.postValue(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

}