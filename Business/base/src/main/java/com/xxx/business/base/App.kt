package com.xxx.business.base

import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.framework.base.BaseApp
import com.xxx.framework.tool.loge
import com.xxx.viewmodel.app.AppViewModel
import com.xxx.viewmodel.app.EventViewModel

class App : BaseApp() {
    companion object {
        lateinit var instance: App
        lateinit var eventViewModelInstance: EventViewModel
        lateinit var appViewModelInstance: AppViewModel
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        eventViewModelInstance = getAppViewModelProvider().get(EventViewModel::class.java)
        appViewModelInstance = getAppViewModelProvider().get(AppViewModel::class.java)
        if (BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        "ARouter初始化${BuildConfig.DEBUG}".loge()
    }
}