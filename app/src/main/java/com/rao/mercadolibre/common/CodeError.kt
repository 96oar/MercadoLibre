package com.rao.mercadolibre.common

import android.util.Log
import com.rao.mercadolibre.R

class CodeError {
    companion object{
        fun evaluateResponseCode(code:Int,TAG:String){
            when (code) {
                404 ->
                    Log.e(TAG, R.string.resource_not_found.toString())

                400 ->
                    Log.e(TAG,R.string.bad_request.toString())

                in 500..599 ->
                    Log.e(TAG,R.string.server_error.toString())

                else ->
                    Log.e(TAG,R.string.unknown_error.toString())

            }
        }
    }
}