package com.example.pracofi.Services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Network {
    companion object {
        fun networkPresent(activity: AppCompatActivity): Boolean {
            val connectivityManager =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
//                        Toast.makeText(
//                            activity,
//                            "NetworkCapabilities.TRANSPORT_CELLULAR",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
//                        Toast.makeText(
//                            activity,
//                            "NetworkCapabilities.TRANSPORT_WIFI",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
//                        Toast.makeText(
//                            activity,
//                            "NetworkCapabilities.TRANSPORT_ETHERNET",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        return true
                    }
                }
            }
            return false
        }
    }
}