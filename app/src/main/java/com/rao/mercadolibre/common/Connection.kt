package com.rao.mercadolibre.common

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.rao.mercadolibre.retrofit.MeLiService


class Connection {
    companion object {
        fun isOnline(conManager: ConnectivityManager): Boolean {
            val networkInfo: NetworkInfo? = conManager.activeNetworkInfo
            val isConnected = networkInfo?.isConnected == true
            if (!isConnected) {
                MeLiService.instance = null
            }
            return isConnected
        }
    }
}