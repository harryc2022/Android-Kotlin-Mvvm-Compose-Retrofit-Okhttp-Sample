package com.xxx.framework.network.manager

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkCallbackImpl: ConnectivityManager.NetworkCallback() {


    val TAG = "NetworkCallbackImpl"

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        Log.i(TAG,"网络已链接")
        NetworkStateManager.instance.mNetworkStateCallback.value?.let {
            if(!it.isSuccess){
                //有网络了
                NetworkStateManager.instance.mNetworkStateCallback.value = NetState(isSuccess = true)
            }
            return
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        Log.i(TAG,"网络已断开")
        NetworkStateManager.instance.mNetworkStateCallback.value?.let {
            if(it.isSuccess){
                //没网
                NetworkStateManager.instance.mNetworkStateCallback.value = NetState(isSuccess = false)
            }
        }
    }


    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            when {
                //WIFFI
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i(TAG,"wifi已经连接")
                }
                //数据流量
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i(TAG,"数据流量已经连接")
                }
                else -> {
                    Log.i(TAG,"其他网络")
                }
            }
        }

    }
}

