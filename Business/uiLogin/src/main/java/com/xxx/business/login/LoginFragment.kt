package com.xxx.business.login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.arouter.XRouterConfig
import com.xxx.business.base.BaseFragment
import com.xxx.viewmodel.LoginViewModel


@Route(path = XRouterConfig.FRAGMENT_URL_LOGIN)
class LoginFragment: BaseFragment<LoginViewModel>() {

    @Preview
    @Composable
    override fun composeUi(){
        Text("login fragment")
    }
}