package com.xxx.business.ui.mine.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.business.ui.mine.composeUtils.ComposeButtom
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.widget.compose.AppColor

/**
 *@Author: Austin
 *@Date:2022-05-05
 *@Pakage:com.xxx.business.ui.mine
 *@Descriptin:  设置
 */
@Route(path = XRouterConfig.FRAGMENT_URL_MINE_SETTING)
class SettingFragment : BaseFragment<BaseViewModel>() {
    override fun currentTitle(): TitleConfig = TitleConfig("设置中心")

    @Composable
    override fun Body() {
        val list = arrayListOf<String>("账号安全中心", "语音", "支付密码", "App应用锁", "震动提醒", "礼物特效", "检测版本：")
        val remenberState = remember { mutableListOf(false, false, false) }
        val checkedStates = remember { mutableStateOf(true) }
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp)
        ) {
            items(list.size) { index ->
                myItem(index, list, remenberState, checkedStates)
            }
            item { 
                ComposeButtom(text = "退出登录", onClick = {

                })
            }
        }
    }

    @Composable
    fun myItem(
        index: Int,
        list: ArrayList<String>,
        remenberState: MutableList<Boolean>,
        checkedStates: MutableState<Boolean>,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 4.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (index >= 3) {
                            checkedStates.value = !checkedStates.value
                        }
                    }
            ) {
                Text(text = list[index], color = Color.Black)
                if (index < 3 || index == list.size - 1) {
                    Icon(
                        Icons.Filled.ArrowRight,
                        contentDescription = null,
                        modifier = Modifier.size(
                            ButtonDefaults.MinHeight
                        )
                    )
                } else {
                    Switch(checked = remenberState[index - 3], onCheckedChange = { b ->
                        if (index >= 3) {
                            checkedStates.value = b
                        }
                    })
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(color = AppColor.cCCCCCC)
            )

        }
    }


}