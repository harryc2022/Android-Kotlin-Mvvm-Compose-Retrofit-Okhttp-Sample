package com.xxx.business.ui.finance

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun GoScreenPreview() {
    PaymentInterfaceScreen()
}



@Composable
fun QQScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 20.dp)
                .height(100.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "没有支付通道",
            color = colorResource(id = R.color.f999999),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QQScreenPreview() {
    QQScreen()
}

@Composable
fun ManualScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 20.dp)
                .height(100.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "没有支付通道",
            color = colorResource(id = R.color.f999999),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ManualScreenPreview() {
    ManualScreen()
}

@Composable
fun UnionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 20.dp)
                .height(100.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "没有支付通道",
            color = colorResource(id = R.color.f999999),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnionScreenPreview() {
    UnionScreen()
}


@Composable
fun AliScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 20.dp)
                .height(100.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "没有支付通道",
            color = colorResource(id = R.color.f999999),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AliScreenPreview() {
    AliScreen()
}

@Composable
fun WeChatScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 20.dp)
                .height(100.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "没有支付通道",
            color = colorResource(id = R.color.f999999),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeChatScreenPreview() {
    WeChatScreen()
}