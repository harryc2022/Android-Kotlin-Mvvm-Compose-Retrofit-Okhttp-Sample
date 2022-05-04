package com.xxx.androidpackage.ui

import android.os.Bundle
import com.xxx.business.arouter.XRouterConfig
import com.xxx.business.base.BaseActivity
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.router.navigation

class SplashActivity : BaseActivity<BaseViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation(XRouterConfig.FRAGMENT_URL_MAIN)
    }
}