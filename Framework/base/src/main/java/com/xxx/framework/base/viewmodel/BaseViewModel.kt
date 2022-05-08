package com.xxx.framework.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xxx.framework.tool.loge
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel() {

    val loadingChange: UiLoadingChange by lazy { UiLoadingChange() }


    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套才加的，不然我加他个鸡儿加
     */
    inner class UiLoadingChange {
        // 显示加载框
        val showDialog: MutableStateFlow<RefreshInfo> = MutableStateFlow(RefreshInfo(false))

        fun showLoading(isLoading: Boolean, message: String? = null) {
            showDialog.value = RefreshInfo(isLoading, message)
            "BaseViewModel---${this@BaseViewModel.javaClass.simpleName} isLoading:${loadingChange.showDialog.value.isLoading}".loge()
        }

        fun showLoading(message: String? = null) {
            showLoading(true,message)
        }

        fun dismissLoading() {
            showLoading(false)
        }
//        隐藏
//        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }

    inner class RefreshInfo(
        val isLoading: Boolean,
        val message: String? = null
    )
}