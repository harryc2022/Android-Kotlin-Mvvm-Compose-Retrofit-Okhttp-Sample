package com.xxx.router

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.business.base.App
import com.xxx.business.protocol.data.RouterInfo

fun Activity.navigation(routerConfig: String) {
    navigation(RouterInfo(routerConfig))
}

fun Activity.navigation(routerInfo: RouterInfo) {
    overridePendingTransition(0, 0)
    startActivity(Intent(this, ContainerActivity::class.java))
    App.appViewModelInstance.routerInfo.value = routerInfo
}

fun Fragment.navigation(routerConfig: String) {
    navigation(RouterInfo(routerConfig))
}

fun Fragment.navigation(routerInfo: RouterInfo) {
    requireActivity().navigation(routerInfo)
}

