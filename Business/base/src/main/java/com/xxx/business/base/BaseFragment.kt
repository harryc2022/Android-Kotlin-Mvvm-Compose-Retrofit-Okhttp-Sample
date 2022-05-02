package com.xxx.business.base

import android.os.Bundle
import android.widget.Toast
import com.xxx.framework.base.activity.BaseVmActivity
import com.xxx.framework.base.fragment.BaseVmFragment
import com.xxx.framework.base.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {

    override fun showLoading(message: String) {
        Toast.makeText(requireContext(), "loading...", Toast.LENGTH_SHORT).show()
    }

    override fun dismissLoading() {
        Toast.makeText(requireContext(), "dismissLoading...", Toast.LENGTH_SHORT).show()
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun lazyLoadData() {
    }

    override fun createObserver() {
    }
}