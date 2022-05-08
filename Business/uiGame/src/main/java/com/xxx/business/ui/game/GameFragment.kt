package com.xxx.business.ui.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.framework.base.viewmodel.BaseViewModel


@Route(path = XRouterConfig.FRAGMENT_URL_GAME)
class GameFragment: BaseFragment<BaseViewModel>() {

    @Preview
    @Composable
    override fun Body(){
       Splash()
    }
}