package com.xxx.business.ui.finance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxx.widget.compose.AppColor
import com.xxx.widget.compose.align

@Composable
fun PaymentInterfaceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        titleContent("请选择支付通道")
        PaymentChannel(list1)

        Divider(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth(),
            color = AppColor.cFDF3F3
        )
        titleContent("请选择充值金额")
        PaymentAmount(list2)

        Box( modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp,end = 10.dp)){
            Text(
                text = "温馨提示:\n" +
                        "近期微信支付宝风控严重,如多次尝试无法成功充值请使用银行转账或人工客服,谢谢\n" +
                        "支付宝固定金额充值，请勿修改充值金额!\n" +
                        "如遇到充值未到账，请及时联系在线客服处理!\n" +
                        "超时支付，重复扫码，修改金额等错误方式无法自动到账!\n", fontSize = 12.sp, color = AppColor.red,
            )
        }
    }
}

val list1 = mutableListOf<String>("30-100", "支付宝小额", "支付宝大额",
       "支付宝小额", "支付宝大额","支付宝小额",
       "支付宝大额", "支付宝小额","支付宝大额",)

val list2 = mutableListOf<String>("30元", "50元", "30元",
    "30元", "30元","30元",
    "30元", "30元","30元",)

// 支付通道
@Composable
private fun PaymentChannel(list1: List<String>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        userScrollEnabled = false
    ) {
        items(list1.size) { index ->
            Card(
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10),
                backgroundColor = Color.White,
                border = BorderStroke(1.dp, color = AppColor.cFDF3F3),
                elevation = 1.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = list1[index], fontSize = 14.sp, color = AppColor.black,
                    )
                }
            }
        }
    }
}


// 支付金额
@Composable
private fun PaymentAmount(list1: List<String>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        userScrollEnabled = false
    ) {
        items(list1.size) { index ->
            Card(
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10),
                backgroundColor = Color.White,
                border = BorderStroke(1.dp, color = AppColor.red),
                elevation = 1.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = list1[index], fontSize = 14.sp, color = AppColor.red,
                    )
                }
            }
        }
    }
}



@Composable
fun titleContent(s: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .height(40.dp)
                .padding(10.dp)
                .align(Alignment.Center)
                .width(4.dp),
            shape = RoundedCornerShape(80),
            backgroundColor = Color.Red,
            elevation = 2.dp
        ) {

        }
        Text(
            text = s,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )
    }
}
