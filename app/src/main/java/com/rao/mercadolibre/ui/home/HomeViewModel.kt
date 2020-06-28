package com.rao.mercadolibre.ui.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.R
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Response

class HomeViewModel() : ViewModel() {
    private var meLiRepository: MeLiRepository = MeLiRepository()

    val productList = MutableLiveData<Response>()
    var message = MutableLiveData<String>()

    fun searchProduct(product: String) {
        //(Product:String , onFailure(call,t), onSuccessful(call,reponse)
            meLiRepository.responseSearch(
                product,
                { _, t ->
                    message.postValue(R.string.error_connection.toString())
                },
                { _, response ->
                    if (response.isSuccessful) {
                        if (!response.body()!!.results.isEmpty()) {
                            productList.postValue(response.body())
                        } else {
                            message.postValue(R.string.no_elements.toString())
                        }
                    } else {
                        message.postValue(CodeError.evaluateResponseCode(response.code()))
                    }
                })
    }

}