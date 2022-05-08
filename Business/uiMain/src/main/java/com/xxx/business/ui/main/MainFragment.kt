package com.xxx.business.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.common.viewmodel.LoginViewModel
import compose.AppScaffold


@Route(path = XRouterConfig.FRAGMENT_URL_MAIN)
class MainFragment : BaseFragment<LoginViewModel>() {

    @Preview
    @Composable
    override fun ComposeUi() {
        AppScaffold(childFragmentManager)
    }
}