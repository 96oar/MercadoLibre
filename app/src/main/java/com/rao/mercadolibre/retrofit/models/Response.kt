package com.rao.mercadolibre.retrofit.models

open class Response(
    val paging: Paging,
    val results: List<Article>,
    val site_id: String
)