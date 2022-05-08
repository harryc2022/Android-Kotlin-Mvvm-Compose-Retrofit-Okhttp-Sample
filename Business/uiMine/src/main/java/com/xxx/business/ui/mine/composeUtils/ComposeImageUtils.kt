package com.xxx.business.ui.mine.composeUtils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xxx.business.ui.mine.R
import com.xxx.business.ui.mine.composeUtils.padding8CenterMaxWidthCircleShape

/**
 *@Author: Austin
 *@Date:2022-05-04
 *@Pakage:com.xxx.androidpackage.ui
 *@Descriptin:
 */

@Composable
fun  ComposeDefImageCircle(@DrawableRes id: Int = com.xxx.viewmodel.R.drawable.ic_launcher_background, padding : Dp = 8.dp , sized: Dp = 42.dp){
    Image(painter = painterResource(id =id),
        contentDescription = null,
        modifier = Modifier
            .size(sized)
            .padding(padding)
            .clip(CircleShape)
    )
}

@Composable
fun  ComposeDefImages(@DrawableRes id: Int =  com.xxx.viewmodel.R.drawable.ic_launcher_background){
  return  Image(painter = painterResource(id =id),
        contentDescription = null,
        modifier =  Modifier.padding8CenterMaxWidthCircleShape()
    )
}


