package com.rao.mercadolibre.common

import com.rao.mercadolibre.R

class CodeError {
    companion object{
        fun evaluateResponseCode(code: Int): String {
            return when (code) {
                404 -> {
                    R.string.resource_not_found.toString()
                }
                400 -> {
                    R.string.bad_request.toString()
                }
                in 500..599 -> {
                    R.string.server_error.toString()
                }
                else -> {
                    R.string.unknown_error.toString()
                }
            }
        }
    }
}