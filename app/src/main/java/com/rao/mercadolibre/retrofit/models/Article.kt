package com.rao.mercadolibre.retrofit.models

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("accepts_mercadopago") val accepts_mercadopago: Boolean,
    @SerializedName("available_quantity") val available_quantity: Int,
    @SerializedName("buying_mode") val buying_mode: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("currency_id") val currency_id: String,
    @SerializedName("id") val id: String,
    @SerializedName("listing_type_id") val listing_type_id: String,
    @SerializedName("permalink") val permalink: String,
    @SerializedName("price") val price: Double,
    @SerializedName("site_id") val site_id: String,
    @SerializedName("sold_quantity") val sold_quantity: Int,
    @SerializedName("stop_time") val stop_time: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("title") val title: String,
    @SerializedName("shipping") val shipping: Shipping
)