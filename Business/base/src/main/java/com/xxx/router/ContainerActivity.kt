package com.xxx.router

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.business.base.App
import com.xxx.business.base.BaseActivity
import com.xxx.business.base.R
import com.xxx.business.protocol.data.RouterInfo
import com.xxx.framework.base.viewmodel.BaseViewModel


class ContainerActivity : BaseActivity<BaseViewModel>() {

    override fun layoutId(): Int {
        return R.layout.container_activity
    }

    override fun createObserver() {
        App.appViewModelInstance.routerInfo.observe(this) {
            Toast.makeText(this, "ContainerActivity:${it.routerConfig}", Toast.LENGTH_SHORT).show()
            switchContent(it)
        }
    }

    private fun switchContent(routerInfo: RouterInfo) {
        val transaction: FragmentTransaction = supportFragmentManager
            .beginTransaction()
        setTransactionAnim(transaction, routerInfo.animIn, routerInfo.animOut)
        val cacheFragment = if (routerInfo.revisit) {
            null
        } else {
            supportFragmentManager.findFragmentByTag(routerInfo.routerConfig)
        }
        cacheFragment?.let {
            transaction.show(it)
        } ?: let {
            val fragment =
                ARouter.getInstance().build(routerInfo.routerConfig).navigation() as Fragment
            transaction.replace(R.id.fl_content, fragment, routerInfo.routerConfig)
        }
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
    }

    private fun setTransactionAnim(
        transaction: FragmentTransaction,
        animIn: Int? = null,
        animOut: Int? = null
    ) {
        if (animIn != null && animOut != null) {
            transaction.setCustomAnimations(animIn, animOut)
        }
    }
}