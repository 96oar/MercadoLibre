package com.rao.mercadolibre.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rao.mercadolibre.R
import com.rao.mercadolibre.common.CodeError
import com.rao.mercadolibre.repository.MeLiRepository
import com.rao.mercadolibre.retrofit.models.Response

class HomeViewModel() : ViewModel() {
    private var meLiRepository: MeLiRepository = MeLiRepository()

    var message: MutableLiveData<Int> = MutableLiveData<Int>()
    val productList = MutableLiveData<Response>()

    fun searchProduct(product: String) {
        meLiRepository.responseSearch(
            product,
            { _, _ ->
                message.postValue(R.string.error_connection)
            },
            { _, response ->
                if (response.isSuccessful) {
                    if(response.body()!!.results.isNotEmpty()) {
                        productList.postValue(response.body())
                    }else{
                        message.postValue(R.string.no_elements)
                    }
                }else{
                    message.postValue(CodeError.evaluateResponseCode(response.code()))
                }
            })
    }

}