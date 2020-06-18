package com.rao.mercadolibre.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
                }
            })
    }

}