package com.xxx.framework.tool

import android.app.Application
import com.tencent.mmkv.MMKV

object AppTool {
    private var application: Application? = null
    fun getAppContext(): Application {
        if (application == null) {
            throw NullPointerException("获取前请初始化")
        }
        return application!!
    }

    fun setApp(application: Application) {
        AppTool.application = application
        MMKV.initialize(application.filesDir.absolutePath + "/mmkv")
    }
}