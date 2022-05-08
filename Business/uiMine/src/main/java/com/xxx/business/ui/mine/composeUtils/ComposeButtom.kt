package com.xxx.business.ui.mine.composeUtils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 *@Author: Austin
 *@Date:2022-05-06
 *@Pakage:com.xxx.business.ui.mine.composeUtils
 *@Descriptin:
 */
@Composable
fun ComposeButtom(text:String,  onClick: () -> Unit, enabled :Boolean = true) {
    Button(
        enabled =enabled,
        onClick = onClick,
        elevation = ButtonDefaults.elevation(disabledElevation = 4.dp),
        modifier = Modifier.fillMaxWidth().padding(12.dp)) {
      Text(text = text)
    }
}