package com.rao.mercadolibre.retrofit.models

data class Paging(
    val limit: Int,
    val offset: Int,
    val primary_results: Int,
    val total: Int
)