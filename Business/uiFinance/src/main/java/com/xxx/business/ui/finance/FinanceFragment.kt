package com.xxx.business.ui.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.accompanist.pager.*
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.common.viewmodel.LoginViewModel
import com.xxx.widget.compose.AppColor
import com.xxx.widget.compose.align
import kotlinx.coroutines.launch
import moe.tlaster.nestedscrollview.VerticalNestedScrollView
import moe.tlaster.nestedscrollview.rememberNestedScrollViewState


@Route(path = XRouterConfig.FRAGMENT_URL_FINANCE)
class FinanceFragment : BaseFragment<LoginViewModel>() {

    override fun currentTitle() = TitleConfig("充值中心", showBackIcon = false)

    @OptIn(ExperimentalMaterialApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
    @Composable
    override fun Body() {
        val nestedScrollViewState = rememberNestedScrollViewState()
        val tabs = listOf(
            TabItem.GOPAY,
            TabItem.QQPAY,
            TabItem.ManualPAY,
            TabItem.QQPAY,
            TabItem.UnionPay,
            TabItem.Alipay,
            TabItem.WeChatPAY
        )
        val pagerState = rememberPagerState()
        VerticalNestedScrollView(
            state = nestedScrollViewState,
            header = {
                Column {
                    Header()
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        color = AppColor.cFDF3F3
                    )
                    Tabs(tabs = tabs, pagerState = pagerState)
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        color = AppColor.cFDF3F3
                    )
                }
            },
            content = {
                Column() {
                    TabsContent(tabs = tabs, pagerState = pagerState)
                }
            }
        )
    }

    // 头部
    @Composable
    private fun Header() {
        Card(
            modifier = Modifier
                .height(120.dp)
                .padding(10.dp)
                .align(Alignment.Center)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10),
            backgroundColor = Color.Red,
            elevation = 2.dp
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "余额", fontSize = 12.sp, color = AppColor.white)
                    Text(text = "0.0", fontSize = 14.sp, color = AppColor.yellow)
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "钻石", fontSize = 12.sp, color = AppColor.white)
                    Text(text = "0", fontSize = 14.sp, color = AppColor.yellow)
                }
                Column(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(text = "兑换钻石", fontSize = 14.sp, color = AppColor.yellow)
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    ScrollableTabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        backgroundColor = Color.White,
        contentColor = Color.White,
        edgePadding = TabRowDefaults.DividerThickness,
        divider = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White),
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        // Add tabs for all of our pages
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            Row(
                modifier = Modifier
                    .height(60.dp)
                    .width(100.dp)
            ) {
                Tab(
                    icon = {
                        Icon(
                            painter = painterResource(id = tab.icon),
                            contentDescription = ""
                        )
                    },
                    text = { Text(tab.title, fontSize = 12.sp, color = AppColor.black) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = AppColor.black,
                    unselectedContentColor = AppColor.c303642,
                    modifier = Modifier
                        .weight(1f)
                )

                Divider(
                    modifier = Modifier
                        .height(60.dp)
                        .width(1.dp),
                    color = AppColor.cFDF3F3
                )
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        TabItem.GOPAY,
        TabItem.QQPAY,
        TabItem.ManualPAY,
        TabItem.QQPAY,
        TabItem.UnionPay,
        TabItem.Alipay,
        TabItem.WeChatPAY
    )
    val pagerState = rememberPagerState()
    Tabs(tabs = tabs, pagerState = pagerState)
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}


@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TabsContentPreview() {
    val tabs = listOf(
        TabItem.GOPAY,
        TabItem.QQPAY,
        TabItem.ManualPAY,
        TabItem.QQPAY,
        TabItem.UnionPay,
        TabItem.Alipay,
        TabItem.WeChatPAY
    )
    val pagerState = rememberPagerState()
    TabsContent(tabs = tabs, pagerState = pagerState)
}
