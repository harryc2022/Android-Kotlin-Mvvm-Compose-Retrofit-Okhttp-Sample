package com.xxx.business.protocol.data

data class RouterInfo(
    val routerConfig: String,
    val revisit: Boolean = false,
    val animIn: Int? = null,
    val animOut: Int? = null
)

