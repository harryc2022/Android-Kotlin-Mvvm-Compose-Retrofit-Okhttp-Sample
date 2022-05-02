package com.xxx.business.login

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.arouter.RouterConstants
import com.xxx.business.base.BaseFragment
import com.xxx.viewmodel.LoginViewModel


@Route(path = RouterConstants.FRAGMENT_URL_LOGIN)
class LoginFragment: BaseFragment<LoginViewModel>() {
    @Composable
    override fun composeUi(){
        Text("login fragment")
    }
}