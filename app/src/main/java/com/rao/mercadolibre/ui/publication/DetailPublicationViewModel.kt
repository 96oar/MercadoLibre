package com.rao.mercadolibre.ui.publication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Detail
import com.rao.mercadolibre.ui.home.HomeViewModel

class DetailPublicationViewModel : ViewModel() {
    val meLiRepository: MeLiRepository = MeLiRepository()

    var detailProduct = MutableLiveData<ArrayList<Detail>>()
    var item = MutableLiveData<Detail>()

    fun getDetailProduct(idProduct: String) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseDetail(
            idProduct,
            { _, t ->
                Log.e("getDetailProductgetDetailProduct", t.message.toString())
            },
            { _, response ->
                if (response.isSuccessful) {
                    detailProduct.postValue(response.body())
                }else{
                    CodeError.evaluateResponseCode(response.code(), DetailPublicationViewModel::class.simpleName.toString())
                }
            })
    }

    fun getItems(idItem: String) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseGetItem(
            idItem,
            { _, t ->
                Log.e("getItems", t.message.toString())
            },
            { _, response ->
                if (response.isSuccessful) {
                    item.postValue(response.body())
                }else{
                    CodeError.evaluateResponseCode(response.code(), DetailPublicationViewModel::class.simpleName.toString())
                }
            })
    }

}