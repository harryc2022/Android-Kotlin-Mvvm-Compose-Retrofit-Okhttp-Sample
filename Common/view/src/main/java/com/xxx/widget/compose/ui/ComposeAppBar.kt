package com.xxx.widget.compose.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.widget.compose.AppColor
import kotlinx.coroutines.flow.MutableStateFlow
import me.onebone.toolbar.*
import okhttp3.internal.wait

/**
 *@Author: Austin
 *@Date:2022-05-06
 *@Pakage:com.xxx.business.ui.mine.composeUtils
 *@Descriptin:
 */

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DefAppBarView(
    contents: @Composable (PaddingValues) -> Unit,
    title: MutableStateFlow<TitleConfig>,
    onClickBack: (() -> Unit)? = null
) {
    Scaffold(
        topBar = if (title.value.showTitle) {
            {
                Column() {
                    Spacer(
                        modifier = Modifier
                            .height(statusBarHeight())//设置状态栏高度
                            .fillMaxWidth()
                            .background(color = AppColor.c4B94FF)
                    )
                    TopAppBar(
                        title = {
                            Text(text = title.value.titleName , fontWeight = FontWeight.Bold,color= Color.White, fontSize = 16.sp)
                        },
                        navigationIcon = if (title.value.showBackIcon) {{
                            IconButton(onClick = {
                                onClickBack?.invoke()
                            }) {
                                Icon(Icons.Filled.ArrowBack, "",tint = Color.White)
                            }
                        }}else{null},
                        backgroundColor = AppColor.c0088FF,
                        contentColor = Color.Black,
                        elevation = 12.dp,
                    )
                }
            }
        } else {
            {
            }
        },
        content = contents
    )
}


@Composable
fun DefAppBarViewCollapsingToolbar(
    contents: @Composable CollapsingToolbarScaffoldScope.() -> Unit,
    titleView : @Composable CollapsingToolbarScope.() -> Unit,
) {
    val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        state = state, // provide the state of the scaffold
        modifier = Modifier,
        scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
        toolbar =titleView ,
        body = contents
    )
}
@Composable
fun statusBarHeight(): Dp {
    return LocalDensity.current.run {
        LocalContext.current.resources.getDimensionPixelSize(
            LocalContext.current.resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
        ).toDp()
    }
}

@Composable
fun navigationBarHeight(): Dp {
    return LocalDensity.current.run {
        LocalContext.current.resources.getDimensionPixelSize(
            LocalContext.current.resources.getIdentifier(
                "navigation_bar_height",
                "dimen",
                "android"
            )
        ).toDp()
    }
}
