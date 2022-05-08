package com.xxx.business.ui.mine.composeUtils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 *@Author: Austin
 *@Date:2022-05-04
 *@Pakage:com.xxx.androidpackage.ui
 *@Descriptin:   TestView  Composable
 */


   @Composable
   fun  ComposeDefTextView(value:String,
                           color: Color = Color.Black,
                           fontSize: TextUnit = 14.sp,
                           modifier :Modifier = Modifier){
   return Text(text = value,
        color = color,
        fontSize = fontSize,
        modifier = modifier
    )
   }