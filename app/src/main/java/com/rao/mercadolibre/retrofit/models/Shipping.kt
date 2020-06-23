package com.rao.mercadolibre.retrofit.models

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("free_shipping") val free_shipping: Boolean,
    @SerializedName("logistic_type") val logistic_type: String,
    @SerializedName("mode") val mode: String,
    @SerializedName("store_pick_up") val store_pick_up: Boolean,
    @SerializedName("tags") val tags: List<String>
)