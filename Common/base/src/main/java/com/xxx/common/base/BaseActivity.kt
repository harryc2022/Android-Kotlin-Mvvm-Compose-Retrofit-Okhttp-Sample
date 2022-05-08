package com.xxx.common.base

import com.xxx.framework.base.activity.BaseVmActivity
import com.xxx.framework.base.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {

}