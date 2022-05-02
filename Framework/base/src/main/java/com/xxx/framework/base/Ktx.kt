package com.xxx.framework.base

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.ProcessLifecycleOwner
import com.tencent.mmkv.MMKV
import com.xxx.framework.ext.lifecycle.KtxAppLifeObserver
import com.xxx.framework.ext.lifecycle.KtxLifeCycleCallBack
import com.xxx.framework.network.manager.NetworkCallbackImpl
import com.xxx.framework.network.manager.NetworkStateReceive
import com.xxx.framework.tool.AppTool
import com.xxx.framework.tool.loge


class Ktx : ContentProvider() {

    companion object {
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun onCreate(): Boolean {
        val application = context!!.applicationContext as Application
        install(application)
        return true
    }

    private fun install(application: Application) {
        AppTool.setApp(application)
        //网络变化监听器
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            val pm: PackageManager = AppTool.getAppContext().packageManager
//            val permission = PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.CHANGE_NETWORK_STATE", AppTool.getAppContext().packageName)
//            if (!permission) {
//                Toast.makeText(AppTool.getAppContext(), "请打开权限", Toast.LENGTH_SHORT).show()
//                val goToSettings = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
//                goToSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                goToSettings.data = Uri.parse("package:" + AppTool.getAppContext().packageName)
//                AppTool.getAppContext().startActivity(goToSettings)
//            }
            try {
                (AppTool.getAppContext()
                    .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager).requestNetwork(
                    NetworkRequest.Builder().build(),
                    NetworkCallbackImpl()
                )
            } catch (e: Exception) {
                "网络监听设置失败".loge()
                e.printStackTrace()
            }
        } else {
            val netFilter = IntentFilter()
            netFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            AppTool.getAppContext().registerReceiver(NetworkStateReceive(), netFilter)
        }

        if (watchActivityLife) application.registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
        if (watchAppLife) ProcessLifecycleOwner.get().lifecycle.addObserver(KtxAppLifeObserver)
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = null


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}