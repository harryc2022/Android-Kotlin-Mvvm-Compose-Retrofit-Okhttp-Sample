package com.xxx.business.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.business.ui.mine.composeUtils.ComposeButtom
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.framework.base.viewmodel.BaseViewModel

/**
 *@Author: Austin
 *@Date:2022-05-06
 *@Pakage:com.xxx.business.ui.mine
 *@Descriptin: 钱包
 */
@Route(path = XRouterConfig.FRAGMENT_URL_MINE_WALLET)
class WalletFragment  : BaseFragment<BaseViewModel>(){
    override fun currentTitle(): TitleConfig = TitleConfig("钱包")


    @Composable
    override fun Body() {

    }

}