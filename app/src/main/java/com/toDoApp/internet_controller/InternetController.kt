package com.toDoApp.internet_controller

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternetController @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    val isInternetConnected: Boolean
        get() {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val network = connectivityManager.activeNetwork
                    if (network != null) {
                        return connectivityManager.getNetworkCapabilities(network)
                            .isNetworkCapabilitiesValid()
                    }
                } else {
                    val networkInfo = connectivityManager.activeNetworkInfo
                    return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
                }
            } catch (_: Exception) {
            }
            return false
        }

    private fun NetworkCapabilities?.isNetworkCapabilitiesValid(): Boolean = when {
        this == null -> false
        hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
                (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> true

        else -> false
    }

}