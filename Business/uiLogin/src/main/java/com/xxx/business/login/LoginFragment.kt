package com.xxx.business.login

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxx.common.router.XRouterConfig
import com.xxx.business.ui.login.R
import com.xxx.common.base.BaseFragment
import com.xxx.common.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.frg_login.*


@Route(path = XRouterConfig.FRAGMENT_URL_LOGIN)
class LoginFragment : BaseFragment<LoginViewModel>() {


    private var mVideoCurrentPosition = 0

    override fun layoutId(): Int {
        return R.layout.frg_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        playBackGroundVideo()
    }

    private fun playBackGroundVideo() {
        val uri = "android.resource://" + requireActivity().packageName + "/" + R.raw.movie
        play(uri)
        vv_bg_video.setOnCompletionListener {
            play(uri)
        }
        vv_bg_video.setOnPreparedListener {
            Handler(Looper.getMainLooper()).post {
                vv_bg_video.seekTo(mVideoCurrentPosition)
                vv_bg_video.start()
            }
        }
    }

    private fun play(uri: String) {
        vv_bg_video.setVideoURI(Uri.parse(uri))
        vv_bg_video.start()
    }


    override fun onResume() {
        super.onResume()
        vv_bg_video.resume()
    }


}