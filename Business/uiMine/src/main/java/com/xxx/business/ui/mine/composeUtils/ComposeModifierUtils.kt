package com.xxx.business.ui.mine.composeUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *@Author: Austin
 *@Date:2022-05-04
 *@Pakage:com.xxx.androidpackage.ui.composeUtils
 *@Descriptin:
 */

 fun Modifier.padding8CenterMaxWidthCircleShape(padding: Dp = 8.dp,sized: Dp = 32.dp,color: Color = Color.Gray):Modifier{
   return this.padding(padding).clip(CircleShape).size(sized).background(color)
 }

fun Modifier.padding8CenterMaxWidth(padding: Dp = 8.dp,color: Color = Color.White):Modifier{
    return this.padding(padding).fillMaxWidth().background(color)
}



