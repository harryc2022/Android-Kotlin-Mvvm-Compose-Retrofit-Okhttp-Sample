package com.xxx.business.ui.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.arouter.XRouterConfig
import com.xxx.business.base.BaseFragment
import com.xxx.framework.theme.SplashComposeTheme
import com.xxx.viewmodel.LoginViewModel


@Route(path = XRouterConfig.FRAGMENT_URL_MAIN)
class MainFragment: BaseFragment<LoginViewModel>() {

    @Preview
    @Composable
    override fun composeUi(){
        SplashComposeTheme {
            Text(text = "MainFragment", color = Color.White)
        }
    }
}