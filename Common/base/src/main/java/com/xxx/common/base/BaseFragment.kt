package com.xxx.common.base

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.LiveData
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.framework.base.fragment.BaseVmFragment
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.widget.compose.ui.DefAppBarView
import com.xxx.widget.compose.ui.RefreshView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {
    lateinit var mRootView: View
    companion object{
        val DATA_BUNDLE_KEY = "DATA_BUNDLE_KEY`"
    }
    /**
     * example :
     * override val titleConfig: TitleConfig by lazy { TitleConfig() }
     */

    open fun currentTitle(): TitleConfig? = null

    private val titleConfig: MutableStateFlow<TitleConfig> =
        MutableStateFlow(currentTitle() ?: TitleConfig(showTitle = false))

    fun setTitle(titleName: String){
        setTitle(TitleConfig(titleName,true))
    }

    fun setNoBackTitle(titleName: String){
        setTitle(TitleConfig(titleName,false))
    }

    fun setTitle(title: TitleConfig) {
        titleConfig.value = title
    }

    override fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            if (!isFirst) {
                mViewModel.loadingChange.showLoading()
            }
            /**
             * do something
             */
            delay(1500)
            mViewModel.loadingChange.dismissLoading()
        }
    }


    @Composable
    open fun ComposeUi() {
        val isRefreshing by mViewModel.loadingChange.showDialog.collectAsState()
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing.isLoading)
        DefAppBarView(contents = {
            RefreshView(swipeRefreshState, contents = { Body() }, getData = {
                initData()
            })
        }, titleConfig, { requireActivity().onBackPressed() })
    }


    override fun composeView(): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent { ComposeUi() }
        }
    }


    @Composable
    open fun Body() {

    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {

    }

    override fun createObserver() {
    }
}