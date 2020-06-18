package com.rao.mercadolibre.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Response

class HomeViewModel() : ViewModel() {
    private var meLiRepository: MeLiRepository = MeLiRepository()

    val productList = MutableLiveData<Response>()

    fun searchProduct(product: String) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseSearch(
            product,
            { _, t ->
                t.message?.let { Log.e("searchProduct", it) }
            },
            { _, response ->
                if (response.isSuccessful) {
                    productList.postValue(response.body())
                }else{
                    CodeError.evaluateResponseCode(response.code(),HomeViewModel::class.simpleName.toString())
                }
            })
    }

}