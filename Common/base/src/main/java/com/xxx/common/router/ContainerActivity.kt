package com.xxx.common.router

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.business.protocol.data.RouterInfo
import com.xxx.common.base.App
import com.xxx.common.base.BaseActivity
import com.xxx.common.base.BaseFragment
import com.xxx.common.base.R
import com.xxx.framework.base.viewmodel.BaseViewModel
import com.xxx.framework.tool.loge
import com.xxx.framework.tool.debugToast
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import kotlinx.android.synthetic.main.container_activity.*


class ContainerActivity : BaseActivity<BaseViewModel>() {

    private var currentFragment: Fragment? = null

    override fun layoutId(): Int {
        return R.layout.container_activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar {
            fitWindow = true
            transparent()
        }
        navigationBar {
            fitWindow = false
        }
    }


    private val showCurrentFragment = Runnable {
        if (supportFragmentManager.fragments.size == 0) {
            navigation(
                RouterInfo(
                    XRouterConfig.FRAGMENT_URL_MAIN,
                    animIn = android.R.anim.fade_in,
                    animOut = android.R.anim.fade_out
                )
            )
            window.decorView.postDelayed({
                iv_welcome.visibility = View.GONE
                navigationBar {
                    fitWindow = true
                    color = android.graphics.Color.BLACK
                }
            }, 300)
        }
    }

    override fun onStart() {
        super.onStart()
        window.decorView.postDelayed(showCurrentFragment, 1200)
    }

    override fun createObserver() {
        App.appViewModelInstance.routerInfo.observeInActivity(this) {
            debugToast("ContainerActivity:${it.routerConfig}")
            switchContent(it)
        }
    }

    override fun onResume() {
        super.onResume()
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
            "${this.javaClass.simpleName}--routerConfig:${routerInfo.routerConfig}".loge()
            showFragment(transaction, it, routerInfo)
        } ?: let {
            "${this.javaClass.simpleName}--routerConfig:${routerInfo.routerConfig}".loge()
            val navigation = ARouter.getInstance().build(routerInfo.routerConfig).navigation()
            navigation?.let {
                showFragment(transaction, it as Fragment, routerInfo)
            } ?: let {
                debugToast("fragment 初始化失败，请检查 路径 及 卸载重装")
                navigation(XRouterConfig.FRAGMENT_URL_MAIN)
                return
            }
        }
    }
    private fun showFragment(
        transaction: FragmentTransaction,
        fragment: Fragment,
        routerInfo: RouterInfo
    ) {
        val arguments = fragment?.arguments?: Bundle()
        arguments.putBundle(BaseFragment.DATA_BUNDLE_KEY,routerInfo.data)
        fragment.arguments = arguments
        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.fl_content, fragment, routerInfo.routerConfig)
        }
        if (currentFragment?.isAdded == true && currentFragment != fragment) {
            transaction.hide(currentFragment!!)
        }
        currentFragment = fragment
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

    override fun onBackPressed() {
        "${this.javaClass.simpleName}--onBackPressed:Container fragment size:${supportFragmentManager.fragments.size}".loge()
        when (supportFragmentManager.fragments.size) {
            0 -> {
                window.decorView.removeCallbacks(showCurrentFragment)
                finish()
            }
            1 -> {
                startActivity(Intent(Intent.ACTION_MAIN).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    addCategory(Intent.CATEGORY_HOME)
                })
            }
            else -> {
                supportFragmentManager.fragments.let {
                    currentFragment = it[it.lastIndex - 1]
                }
                supportFragmentManager.popBackStack()
            }
        }
    }
}