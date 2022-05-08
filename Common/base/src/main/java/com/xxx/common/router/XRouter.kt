package com.xxx.common.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.business.protocol.data.RouterInfo
import com.xxx.common.base.App

fun Activity.navigation(routerConfig: String) {
    navigation(RouterInfo(routerConfig))
}

fun Activity.navigation(routerConfig: String, bundle: Bundle) {
    navigation(RouterInfo(routerConfig, data = bundle))
}

fun Activity.navigation(routerInfo: RouterInfo) {
    App.appViewModelInstance.routerInfo.value = routerInfo
    startActivity(Intent(this, ContainerActivity::class.java))
    overridePendingTransition(0, 0)
}


fun Fragment.navigation(routerConfig: String) {
    navigation(RouterInfo(routerConfig))
}

fun Fragment.navigation(routerConfig: String, bundle: Bundle) {
    navigation(RouterInfo(routerConfig, data = bundle))
}

fun Fragment.navigation(routerInfo: RouterInfo) {
    requireActivity().navigation(routerInfo)
}

