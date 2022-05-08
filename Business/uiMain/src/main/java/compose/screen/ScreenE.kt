package compose.screen

import Assets
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xxx.common.base.App
import com.xxx.widget.compose.*

@Composable
fun Splash() {
    Scaffold(backgroundColor = AppColor.c1B1C21,
        topBar = {
            TopBar()
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) { Body() }
    }
}

@Composable
fun TopBar() {
    Container(
        Modifier.shadowF(
            AppColor.c80000000, offsetY = 2.dp,
            cornersRadius = 2.dp,
            shadowBlurRadius = 4.dp
        ),
        align = Alignment.CenterStart,
        background = AppColor.main,
        height = 56.dp,
        clickable = false,
        padding = PaddingValues(start = 16.dp),
        width = Container.MATCH_CONTENT,
        child = {
            Text(
                text = "Game screen", color = AppColor.white, modifier = Modifier.align(
                    Alignment.CenterStart
                ), style = textSize12
            )
        }
    )
}

@Composable
fun Body() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AssetImage(
                path = Assets.icPortfolio,
                w = 64.dp
            )
            GapsH(vl = 10.dp)
            Column {
                Text(
                    text = "用户666888",
                    style = textSize20.copy(color = AppColor.c696D91)
                )
                GapsV(vl = 4.dp)
                AssetImage(
                    path = Assets.icDimond,
                    w = 24.dp
                )
            }
            GapsH(Modifier.weight(1f))
            AssetImage(
                path = Assets.icSignedIn,
                w = 100.dp,
                h = 44.dp
            )
        }
        GapsV(vl = 18.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            Container(
                Modifier.shadowF(
                    AppColor.c80000000, offsetY = 2.dp,
                    cornersRadius = 2.dp,
                    shadowBlurRadius = 4.dp
                ),
                align = Alignment.TopStart,
                width = 108.dp,
                shape = RoundedCornerShape(2.dp),
                background = AppColor.c2B2B2B,
                padding = PaddingValues(8.dp),
                child = {
                    Column {
                        Text(
                            style = textSize12.copy(color = AppColor.cE9C55E),
                            text = "钱包余额"
                        )
                        Text(
                            style = textSize20.copy(
                                color = AppColor.cE9C55E,
                                fontWeight = FontWeight.Bold
                            ),
                            text = "8888.00"
                        )
                    }
                },
                onClick = {
                    toast("Hello")
                }
            )
            GapsH(vl = 10.dp)
            Container(
                Modifier
                    .weight(1f)
                    .shadowF(
                        AppColor.c80000000, offsetY = 2.dp,
                        cornersRadius = 2.dp,
                        shadowBlurRadius = 4.dp
                    ),
                clickable = false,
                align = Alignment.TopStart,
                width = Container.MATCH_CONTENT,
                height = Container.MATCH_CONTENT,
                shape = RoundedCornerShape(2.dp),
                background = AppColor.cE9C55E,
                padding = PaddingValues(8.dp),
                child = {
                    Row {
                        Column {
                            Text(
                                style = textSize12.copy(color = AppColor.c121212),
                                text = "每日观影次数"
                            )
                            GapsH(vl = 6.dp)
                            Text(
                                style = textSize16.copy(
                                    color = AppColor.c7A460D,
                                    fontWeight = FontWeight.Bold
                                ),
                                text = "无限观影"
                            )
                        }
                        GapsH(vl = 16.dp)
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                style = textSize13.copy(
                                    color = AppColor.c7A460D,
                                    fontWeight = FontWeight.Bold
                                ),
                                text = "2026.06.06:到期"
                            )
                            GapsH(vl = 6.dp)
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Container(
                                    Modifier
                                        .shadowF(
                                            AppColor.c80000000, offsetY = 1.dp,
                                            cornersRadius = 11.dp,
                                            shadowBlurRadius = 2.dp
                                        ),
                                    align = Alignment.TopStart,
                                    height = 22.dp,
                                    border = borderH(
                                        w = 1.dp,
                                        colors = listOf
                                            (
                                            AppColor.cEEC588,
                                            AppColor.cFBE0B4
                                        )
                                    ),
                                    shape = RoundedCornerShape(11.dp),
                                    gradient = gradientH(
                                        listOf(
                                            AppColor.cEEC588,
                                            AppColor.cFBE0B4
                                        )
                                    ),
                                    padding = PaddingValues(horizontal = 8.dp, vertical = 3.dp),
                                    child = {
                                        Text(
                                            style = textSize12.copy(color = AppColor.c7A460D),
                                            text = "立即续费"
                                        )
                                    }
                                )
                                GapsH(vl = 4.dp)
                                Text(
                                    style = textSize12.copy(color = AppColor.cFF4B5C),
                                    text = "VIP六大特权"
                                )
                            }
                        }
                    }
                },
                onClick = {
                    toast("Hello")
                }
            )
        }
    }
}

fun toast(s: String) {
    Toast.makeText(App.instance, s, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun ComposableGame() {
    Splash()
}














