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

    fun searchProduct(product: String,
                      onFailure:(message:String)-> Unit) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
        meLiRepository.responseSearch(
            product,
            { _, t ->
                onFailure(t.message.toString())
            },
            { _, response ->
                if (response.isSuccessful) {
                    if(!response.body()!!.results.isEmpty()) {
                        productList.postValue(response.body())
                    }else{
                        onFailure("No se encontraron elementos.")
                    }
                }else{
                    onFailure(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

}