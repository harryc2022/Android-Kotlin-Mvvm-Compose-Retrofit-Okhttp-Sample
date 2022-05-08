package com.xxx.framework.tool

import android.widget.Toast
import com.xxx.tool.BuildConfig

fun debugToast(s: String) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(AppTool.getAppContext(), s, Toast.LENGTH_SHORT).show()
    }
}