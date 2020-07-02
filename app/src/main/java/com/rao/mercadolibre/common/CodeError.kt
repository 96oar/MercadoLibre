package com.rao.mercadolibre.common

import com.rao.mercadolibre.R

class CodeError {
    companion object{
        fun evaluateResponseCode(code: Int): Int {
            return when (code) {
                404 -> {
                    R.string.resource_not_found
                }
                400 -> {
                    R.string.bad_request
                }
                in 500..599 -> {
                    R.string.server_error
                }
                else -> {
                    R.string.unknown_error
                }
            }
        }
    }
}