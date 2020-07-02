package com.rao.mercadolibre.retrofit.models

import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("primary_results") val primary_results: Int,
    @SerializedName("total") val total: Int
)