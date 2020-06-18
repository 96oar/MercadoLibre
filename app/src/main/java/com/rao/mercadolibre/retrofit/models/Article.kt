package com.rao.mercadolibre.retrofit.models

data class Article(
    val accepts_mercadopago: Boolean,
    val available_quantity: Int,
    val buying_mode: String,
    val condition: String,
    val currency_id: String,
    val id: String,
    val listing_type_id: String,
    val permalink: String,
    val price: Double,
    val site_id: String,
    val sold_quantity: Int,
    val stop_time: String,
    val thumbnail: String,
    val title: String,
    val shipping: Shipping
)