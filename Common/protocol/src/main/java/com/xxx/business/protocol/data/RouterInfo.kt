package com.xxx.business.protocol.data

import android.os.Bundle

data class RouterInfo(
    val routerConfig: String,
    val revisit: Boolean = false,
    val data: Bundle? = null,
    val animIn: Int? = null,
    val animOut: Int? = null
)

