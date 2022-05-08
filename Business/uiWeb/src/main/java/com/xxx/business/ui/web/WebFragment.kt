package com.xxx.business.ui.web

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.framework.base.viewmodel.BaseViewModel


@Route(path = XRouterConfig.FRAGMENT_URL_WEB)
class WebFragment : BaseFragment<BaseViewModel>() {

    @Composable
    override fun Body() {
        Text(text = "WebFragment", color = Color.White)
    }
}