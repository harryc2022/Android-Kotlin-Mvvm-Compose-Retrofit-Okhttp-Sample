package com.xxx.business.ui.mine

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.business.ui.mine.composeUtils.*
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.common.router.navigation
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.framework.tool.debugToast
import com.xxx.widget.compose.AppColor
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState

@Route(path = XRouterConfig.FRAGMENT_URL_MINE)
class MineFragment : BaseFragment<BaseViewModel>() {

    override fun currentTitle() = TitleConfig("个人中心", showBackIcon = false)

    @Composable
    override fun Body() {
        val nestedScrollViewState = rememberNestedScrollViewState()
        VerticalNestedScrollView(
            state = nestedScrollViewState,
            header = {
                Column() {
                    Header()
                    Focus()
                }
            },
            content = {
                Column() {
                    ExtraView()
                    BottomList()
                }
            }
        )
    }

    // 头部
    @Composable
    private fun Header() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navigation(XRouterConfig.FRAGMENT_URL_MINE_EDITUSERINFO)
                },
            shape = RoundedCornerShape(0),
            backgroundColor = Color.White,
            elevation = 2.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier.size(
                            ButtonDefaults.MinWidth + ButtonDefaults.IconSize
                        ),
                    ) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = null,
                            tint = AppColor.c6D6E72,
                            modifier = Modifier.size(
                                ButtonDefaults.MinWidth + ButtonDefaults.IconSize
                            )
                        )
                        Text(
                            text = "编辑个人资料",
                            color = AppColor.cD8DDFF,
                            fontSize = 8.sp,
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .background(
                                    color = AppColor.c5E5E5E,
                                    shape = RoundedCornerShape(24.dp),
                                )
                                .border(
                                    width = 1.dp,
                                    color = AppColor.cD8DDFF,
                                    shape = RoundedCornerShape(24.dp),
                                )
                                .padding(
                                    horizontal = 8.dp,
                                    vertical = 2.dp
                                )

                        )
                    }


                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Row() {
                            ComposeDefTextView(
                                value = "UserName",
                                modifier = Modifier.requiredWidth(100.dp)
                            )
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = null,
                                tint = AppColor.c6D6E72,
                                modifier = Modifier
                                    .size(
                                        ButtonDefaults.IconSize
                                    )
                                    .clickable {
                                        navigation(XRouterConfig.FRAGMENT_URL_MINE_SETTING)
                                    })
                        }
                        Text(
                            text = "VIP 1",
                            fontWeight = FontWeight.Bold,
                            color = AppColor.c6D6E72,
                            fontSize = 16.sp
                        )
                        ComposeDefTextView(
                            value = "ID:123456",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }

    // 关注  粉丝
    @Composable
    private fun Focus() {
        val list1 = mutableListOf<String>("关注", "粉丝")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(list1.size) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "10",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AppColor.c6D6E72
                    )
                    Text(text = list1[index], fontSize = 10.sp, color = AppColor.c6D6E72)
                }
            }
        }
    }

    // 充值等其他功能
    @Composable
    private fun ExtraView() {
        val list1 = mutableListOf<String>("钱包", "贵族", "活动", "活动中心", "推广赚钱")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(list1.size) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ComposeDefImageCircle()
                    Text(text = list1[index], fontSize = 12.sp, color = AppColor.c6D6E72)
                }
            }
        }
    }

    //  底部的工具
    @Composable
    private fun BottomList() {
        val list1 = mutableListOf<String>("收入明细", "游戏记录")
        val list2 = mutableListOf<String>("手机认证", "我的背包", "等级", "专属客服", "设置")
        Column(modifier = Modifier.background(color = Color.White)) {
            BottomList(list2)
            BottomList(list1)
        }
    }

    //  底部的工具
    @Composable
    private fun BottomList(list: MutableList<String>) {
        Card(
            modifier = Modifier.padding(8.dp),
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                items(list.size) { index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            when (index) {
                                0 -> {
                                    navigation(XRouterConfig.FRAGMENT_URL_MINE_PHONEAUTH)
                                }
                                1 -> {
                                    navigation(XRouterConfig.FRAGMENT_URL_MINE_WALLET)
                                }
                                2 -> {
                                    navigation(XRouterConfig.FRAGMENT_URL_MINE_WALLET)
                                }
                                3 -> {
                                    navigation(XRouterConfig.FRAGMENT_URL_MINE_WALLET)
                                }
                                4 -> {
                                    navigation(XRouterConfig.FRAGMENT_URL_MINE_SETTING)
                                }

                            }
                        }) {
                        ComposeDefImages()
                        ComposeDefTextView(
                            value = list[index], modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        )
                        Icon(
                            Icons.Filled.ArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(
                                ButtonDefaults.MinHeight
                            )
                        )
                    }
                    if (index != list.size - 1) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .background(color = AppColor.cCCCCCC)
                        )
                    }
                }
            }
        }
    }


}