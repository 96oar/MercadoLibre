package com.rao.mercadolibre.retrofit.models

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("id") val id: String,
    @SerializedName("max_size") val max_size: String,
    @SerializedName("quality") val quality: String,
    @SerializedName("secure_url") val secure_url: String,
    @SerializedName("size") val size: String,
    @SerializedName("url") val url: String
)