package com.rao.mercadolibre.retrofit

import com.rao.mercadolibre.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MeLiClient {
    private val meLiService : MeLiService
    private val retrofit : Retrofit

    companion object{
        var instance : MeLiClient? = null
            get() {
                if(field == null){
                    instance =
                        MeLiClient()
                }
                return field
            }
    }

    init {
        // Construir el cliente de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.MELI_URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Instanciamos el servicio de retrofit a partir de su objeto
        meLiService = retrofit.create(MeLiService::class.java)
    }

    fun getMeLiService() = meLiService

}