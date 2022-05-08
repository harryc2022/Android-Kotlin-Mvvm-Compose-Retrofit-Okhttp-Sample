package com.xxx.business.ui.finance

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int, var title: String, var screen: ComposableFun) {
    object JINGDONG : TabItem(R.drawable.ic_music, "京东", { PaymentInterfaceScreen() })
    object QQPAY : TabItem(R.drawable.ic_music, "QQ", { QQScreen() })
    object ManualPAY : TabItem(R.drawable.ic_music, "人工充值", { ManualScreen() })
    object UnionPay : TabItem(R.drawable.ic_music, "银联", { UnionScreen() })
    object Alipay : TabItem(R.drawable.ic_movie, "支付宝", { AliScreen() })
    object WeChatPAY : TabItem(R.drawable.ic_book, "微信", { WeChatScreen() })
}