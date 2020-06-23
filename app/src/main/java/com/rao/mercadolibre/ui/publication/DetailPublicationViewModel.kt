package com.rao.mercadolibre.ui.publication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Detail

class DetailPublicationViewModel : ViewModel() {
    private val meLiRepository: MeLiRepository = MeLiRepository()

    var detailProduct = MutableLiveData<ArrayList<Detail>>()
    var item = MutableLiveData<Detail>()

    fun getDetailProduct(
        idProduct: String,
        onFailure: (message: String) -> Unit
    ) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseDetail(
            idProduct,
            { _, t ->
                onFailure(t.message.toString())
            },
            { _, response ->
                if (response.isSuccessful) {
                    detailProduct.postValue(response.body())
                } else {
                    onFailure(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

    fun getItems(idItem: String,
    onFailure: (message: String) -> Unit) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseGetItem(
            idItem,
            { _, t ->
                onFailure(t.message.toString())
            },
            { _, response ->
                if (response.isSuccessful) {
                    item.postValue(response.body())
                }else{
                    onFailure(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

}