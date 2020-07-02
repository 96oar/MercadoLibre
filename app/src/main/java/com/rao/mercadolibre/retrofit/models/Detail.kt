package com.rao.mercadolibre.retrofit.models

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("id") val id: String,
    @SerializedName("created") val created: String,
    @SerializedName("plain_text") val plain_text: String,
    @SerializedName("pictures") val pictures: ArrayList<Picture>
)