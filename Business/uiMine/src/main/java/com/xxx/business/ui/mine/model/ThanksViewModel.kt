package com.xxx.business.ui.mine.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

/**
 *@Author: Austin
 *@Date:2022-05-07
 *@Pakage:com.xxx.business.ui.mine.model
 *@Descriptin:
 */
class ThanksViewModel : ViewModel() {
    suspend fun getDataInfo() : Int {
        delay(5000)
        return  10
    }
}