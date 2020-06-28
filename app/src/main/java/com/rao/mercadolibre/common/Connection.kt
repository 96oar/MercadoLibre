package com.rao.mercadolibre.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


@Suppress("DEPRECATION")
class Connection {

        fun isOnline(conManager:ConnectivityManager) : Boolean {
            var networkInfo = conManager.activeNetworkInfo
            return (networkInfo != null && networkInfo.isConnectedOrConnecting)
        }
}