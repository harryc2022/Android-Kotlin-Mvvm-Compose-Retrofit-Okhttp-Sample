package com.xxx.widget.compose.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxx.common.view.R
import com.xxx.widget.compose.AppColor
import com.xxx.widget.compose.align

/**
 *@Author: Austin
 *@Date:2022-05-07
 *@Pakage:com.xxx.widget.compose.ui
 *@Descriptin:  Empty View
 */

@Composable
fun EmptyView(
    msg: String = "暂无数据",
    @DrawableRes id: Int = R.drawable.safe_ic,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(align = Alignment.Center)
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(color = AppColor.white)
                .align(align = Alignment.Center)
                .padding(12.dp)
                .clickable {
                    onClick()
                }
        ) {
            Column(
                modifier = Modifier
                    .align(align = Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = id),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(12.dp)
                )
                Text(
                    modifier = Modifier
                        .align(align = Alignment.Center)
                        .padding(12.dp),
                    text = msg,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
    }
}
