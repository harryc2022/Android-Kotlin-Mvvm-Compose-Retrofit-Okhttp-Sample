package com.xxx.androidpackage.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.xxx.business.base.BaseActivity
import com.xxx.framework.base.viewmodel.BaseViewModel

class SplashActivity : BaseActivity<BaseViewModel>() {

//    override fun layoutId():Int {
//        return android.R.layout.list_content
//    }

    @Preview
    @Composable
    override fun composeUi() {
        MaterialTheme {
            Text(text = "欢迎页", color = Color.White)
        }
    }
}