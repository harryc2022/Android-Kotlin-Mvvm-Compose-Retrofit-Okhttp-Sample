package com.xxx.business.base

import android.widget.Toast
import com.xxx.framework.base.activity.BaseVmActivity
import com.xxx.framework.base.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {

    override fun showLoading(message: String) {
        Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show()
    }

    override fun dismissLoading() {
        Toast.makeText(this, "dismissLoading...", Toast.LENGTH_SHORT).show()
    }
}