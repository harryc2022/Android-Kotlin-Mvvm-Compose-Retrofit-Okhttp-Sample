package com.xxx.business.ui.mine

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.widget.compose.AppColor
import com.xxx.widget.compose.ui.statusBarHeight
import me.onebone.toolbar.*

/**
 *@Author: Austin
 *@Date:2022-05-06
 *@Pakage:com.xxx.business.ui.mine
 *@Descriptin:  Edit  User
 */
@Route(path = XRouterConfig.FRAGMENT_URL_MINE_EDITUSERINFO)
class EditUserInfoFragment : BaseFragment<BaseViewModel>() {
    @Composable
    override fun Body() {
        EditUserInfoView()
    }

    @Composable
    private fun EditUserInfoView() {
        DefAppBarViewCollapsingToolbar()
    }

    @Composable
    fun DefAppBarViewCollapsingToolbar() {
        val state = rememberCollapsingToolbarScaffoldState()
        val alpha: Float by animateFloatAsState(state.toolbarState.progress)
        CollapsingToolbarScaffold(
            state = state, // provide the state of the scaffold
            modifier = Modifier.background(color = androidx.compose.ui.graphics.Color.Transparent),
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val textSize = (14 + (18 - 14) * state.toolbarState.progress).sp
                val modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth()
                    .height(240.dp + statusBarHeight())
                    .pin()
                Box(
                    modifier = modifier
                )
                Image(
                    painter =
                    painterResource(
                        id = com.xxx.common.base.R.drawable.ic_launcher_background
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .animateContentSize()
                        .graphicsLayer(alpha = alpha),
                    contentScale = ContentScale.FillWidth
                )
//                Card(
//                    elevation = 8.dp,
//                    modifier = Modifier
//                        .road(Alignment.CenterStart, Alignment.BottomCenter)
//                        .fillMaxWidth().height(8.dp)
//                ){
//                    Modifier.fillMaxWidth().fillMaxWidth().height(8.dp)
//                }

                Column(
                    modifier = Modifier
//                        .road(Alignment.CenterStart, Alignment.BottomCenter)
                        .padding(38.dp, statusBarHeight(), 16.dp, 16.dp)
                        .animateContentSize()
                        .graphicsLayer(alpha = alpha),
                ) {
                    Row() {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = null,
                            tint = AppColor.c6D6E72,
                            modifier = Modifier
                                .size(
                                    ButtonDefaults.MinWidth + ButtonDefaults.IconSize
                                )
                        )
                        Text(
                            text = "UserName",
//                            modifier = Modifier
////                        .road(Alignment.CenterStart, Alignment.BottomCenter)
//                                .padding(60.dp, 16.dp + statusBarHeight(), 16.dp, 16.dp),
                            color = AppColor.white,
                            fontSize = textSize
                        )
                    }
                }

                AnimatedVisibility(
                    visible = alpha == 0.0f,
                    enter = slideInHorizontally() + fadeIn(),
                    exit = slideOutHorizontally() + fadeOut(),
                ) {
                    Text(
                        text = "编辑资料",
                        color = AppColor.white,
                        fontSize = 18.sp,
                        modifier = Modifier
//                        .road(Alignment.CenterStart, Alignment.BottomCenter)
                            .padding(42.dp, 12.dp + statusBarHeight(), 16.dp, 16.dp),
                        fontWeight = FontWeight.Bold
                    )

                }
                IconButton(
                    onClick = {
                        requireActivity().onBackPressed()
                    },
                    modifier = Modifier.padding(top = statusBarHeight())
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        "",
                        tint = androidx.compose.ui.graphics.Color.White
                    )
                }

            }
        ) {
            BoomEditView()
        }
    }

    @Composable
    private fun BoomEditView() {
           LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
               items(20) { i ->
                   Card(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(10.dp) ,
                       elevation = 10.dp
                   ) {
                       Text(text = "Value  $i", modifier = Modifier.padding(10.dp))
                   }
               }
           }
    }


}