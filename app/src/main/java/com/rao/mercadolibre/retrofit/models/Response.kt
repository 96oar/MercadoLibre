package com.rao.mercadolibre.retrofit.models

import com.google.gson.annotations.SerializedName

open class Response(
    @SerializedName("paging") val paging: Paging,
    @SerializedName("results") val results: List<Article>,
    @SerializedName("site_id") val site_id: String
)