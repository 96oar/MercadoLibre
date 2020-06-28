package com.rao.mercadolibre.repository

import com.rao.mercadolibre.retrofit.MeLiService
import com.rao.mercadolibre.retrofit.models.Detail
import com.rao.mercadolibre.retrofit.models.Response
import retrofit2.Call
import retrofit2.Callback

class MeLiRepository {
    private var meLiService: MeLiService? = null

    init {
        meLiService = MeLiService.instance
    }

    fun responseSearch(
        product: String,
        onFailure: (call: Call<Response>, t: Throwable) -> Unit,
        onResponse: (call: Call<Response>, response: retrofit2.Response<Response>) -> Unit
    ) {
        meLiService?.searchProduct(product)?.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                onFailure(call, t);
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                onResponse(call, response)
            }
        })
    }

    fun responseDetail(
        idProduct: String,
        onFailure: (call: Call<ArrayList<Detail>>, t: Throwable) -> Unit,
        onResponse: (call: Call<ArrayList<Detail>>, response: retrofit2.Response<ArrayList<Detail>>) -> Unit
    ){
        meLiService?.getDetailProduct(idProduct)?.enqueue(object: Callback<ArrayList<Detail>>{
            override fun onFailure(call: Call<ArrayList<Detail>>, t: Throwable) {
                onFailure(call,t)
            }

            override fun onResponse(call: Call<ArrayList<Detail>>, response: retrofit2.Response<ArrayList<Detail>>) {
                onResponse(call,response)
            }
        })
    }

    fun responseGetItem(
        idItem:String,
        onFailure: (call: Call<Detail>, t: Throwable) -> Unit,
        onResponse: (call: Call<Detail>, response: retrofit2.Response<Detail>) -> Unit
    ){
        meLiService?.getItems(idItem)?.enqueue(object:Callback<Detail>{
            override fun onFailure(call: Call<Detail>, t: Throwable) {
                onFailure(call,t)
            }

            override fun onResponse(call: Call<Detail>, response: retrofit2.Response<Detail>) {
                onResponse(call,response)
            }
        })
    }
}