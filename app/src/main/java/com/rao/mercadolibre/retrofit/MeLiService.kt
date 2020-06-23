package com.rao.mercadolibre.retrofit

import com.rao.mercadolibre.common.Constants
import com.rao.mercadolibre.retrofit.models.Detail
import com.rao.mercadolibre.retrofit.models.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MeLiService {
    companion object{
        var instance : MeLiService? = null
        get() {
            if(field == null){
                // Construir el cliente de Retrofit
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.MELI_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                // Instanciamos el servicio de retrofit a partir de su objeto
                instance = retrofit.create(MeLiService::class.java)
            }
            return field
        }
    }

    @GET("sites/MLA/search")
    fun searchProduct(@Query("q") query : String) : Call<Response>

    @GET("items/{id}/descriptions")
    fun getDetailProduct(@Path("id")id:String) : Call<ArrayList<Detail>>

    @GET("items/{id}")
    fun getItems(@Path("id")id:String):Call<Detail>

}