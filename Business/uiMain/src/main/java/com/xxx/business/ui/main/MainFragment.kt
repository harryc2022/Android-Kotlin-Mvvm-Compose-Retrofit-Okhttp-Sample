package com.xxx.business.ui.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.arouter.RouterConstants
import com.xxx.business.base.BaseFragment
import com.xxx.viewmodel.LoginViewModel


@Route(path = RouterConstants.FRAGMENT_URL_Main)
class MainFragment: BaseFragment<LoginViewModel>() {
    @Composable
    override fun composeUi(){
        Text("main fragment")
    }
}