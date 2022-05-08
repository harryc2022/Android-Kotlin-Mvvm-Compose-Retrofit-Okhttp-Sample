package com.xxx.business.ui.mine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
 *@Descriptin:
 */

@Route(path = XRouterConfig.FRAGMENT_URL_MINE_PHONEAUTH)
class PhoneAuthFragment : BaseFragment<BaseViewModel>() {


    override fun currentTitle(): TitleConfig = TitleConfig("手机认证")

    @Composable
    override fun Body() {
        PhoenAuthView()
    }

    @Composable
    private fun PhoenAuthView() {
        val keyboards =  LocalTextInputService.current
        val phoneNumber = remember { mutableStateOf("") }
        val buttonsText = "手机认证"
        Column() {
            OutlinedTextField(
                isError = phoneNumber.value.length !=11,
                textStyle = TextStyle.Default,
                singleLine = true,
                placeholder = {
                    Text("请输入手机号码")
                },
                value = phoneNumber.value,
                onValueChange = {
                    if (it.length > 11){
                        return@OutlinedTextField
                    }
                    phoneNumber.value = it
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )
            ComposeButtom(buttonsText, {
                keyboards?.hideSoftwareKeyboard()
                requireActivity().onBackPressed()
            },
                enabled = phoneNumber.value.length ==11,
            )
        }
    }
}