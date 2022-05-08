package com.xxx.business.ui.mine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.business.protocol.data.TitleConfig
import com.xxx.business.ui.mine.composeUtils.LoadingContent
import com.xxx.business.ui.mine.model.ThanksViewModel
import com.xxx.common.base.BaseFragment
import com.xxx.common.router.XRouterConfig
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.widget.compose.align
import com.xxx.widget.compose.ui.EmptyView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *@Author: Austin
 *@Date:2022-05-07
 *@Pakage:com.xxx.business.ui.mine
 *@Descriptin:
 */
@Route(path = XRouterConfig.FRAGMENT_URL_MINE_COMMENTLIST)
class CommentListFragment : BaseFragment<BaseViewModel>() {

    @Composable
    override fun Body() {
        CommentList()
    }
    @Composable
    private fun CommentList() {
        LoadingMore()
        val itemSize = remember {
            mutableStateOf(0)
        }
        LazyColumn() {
            item {
                if (itemSize.value == 0) {
                    EmptyView(onClick = {
                    })
                }
            }
            items(itemSize.value) { index ->
                Card(
                    modifier = Modifier.padding(8.dp),
                    elevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Text(text = "Name:  $index", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Content  $index")
                    }
                }
            }
        }
    }
    @Composable
    private  fun LoadingMore(){
        val vm : ThanksViewModel = viewModel()
        val list = arrayListOf<String>()
        LoadingContent(
            modifier = Modifier.align(align = Alignment.Center),
            loader ={ vm.getDataInfo()},
        ) { data->

        }
    }


    @Composable
    private fun CommentItem() {

    }



}