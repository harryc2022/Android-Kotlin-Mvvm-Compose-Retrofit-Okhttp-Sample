package com.xxx.widget.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.xxx.framework.base.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *@Author: Austin
 *@Date:2022-05-05
 *@Pakage:com.xxx.androidpackage.ui.composeUtils
 *@Descriptin:
 */


@Composable
fun RefreshView(
    state: SwipeRefreshState,
    contents: @Composable () -> Unit,
    getData: () -> Unit,
) {
    SwipeRefresh(state = state, onRefresh = {
        CoroutineScope(Dispatchers.IO).launch {
            getData()
        }
    }, content = contents)
}