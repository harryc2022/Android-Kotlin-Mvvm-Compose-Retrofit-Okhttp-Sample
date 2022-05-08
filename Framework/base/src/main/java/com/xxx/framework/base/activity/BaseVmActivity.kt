package com.xxx.framework.base.activity

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.framework.ext.getVmClazz
import com.xxx.framework.network.manager.NetState
import com.xxx.framework.network.manager.NetworkStateManager
import com.xxx.framework.tool.loge
import kotlinx.coroutines.flow.onCompletion

abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    open fun layoutId(): Int? = null

    open fun layoutView(): View? = null

    //有的界面控制ComposeUi是否运行
    open fun supportCompose(): Boolean = true

    @Composable
    open fun composeUi() {
    }


    open fun initView(savedInstanceState: Bundle?) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "Base Log:当前在${this.javaClass.simpleName}".loge()
        layoutId()?.let { layoutId ->
            setContentView(layoutId)
        } ?: layoutView()?.let {
            setContentView(it)
        } ?: let {
            if (supportCompose()) {
                setContent {
                    composeUi()
                }
            }
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
//        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observeInActivity(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    open fun createObserver() {}

}