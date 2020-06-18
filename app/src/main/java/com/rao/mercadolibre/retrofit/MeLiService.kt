package com.rao.mercadolibre.retrofit

import com.rao.mercadolibre.retrofit.models.Detail
import com.rao.mercadolibre.retrofit.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeLiService {

    @GET("sites/MLA/search")
    fun searchProduct(@Query("q") query : String) : Call<Response>

    @GET("items/{id}/descriptions")
    fun getDetailProduct(@Path("id")id:String) : Call<ArrayList<Detail>>

    @GET("items/{id}")
    fun getItems(@Path("id")id:String):Call<Detail>

}