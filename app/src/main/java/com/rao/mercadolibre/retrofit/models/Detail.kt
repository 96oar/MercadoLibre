package com.rao.mercadolibre.retrofit.models

data class Detail (
    val id : String,
    val created : String,
    val plain_text : String,
    val pictures:ArrayList<Picture>
)