package compose.screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.alibaba.android.arouter.launcher.ARouter
import com.xxx.business.ui.main.R
import com.xxx.business.ui.main.databinding.FragmentScreenBinding
import com.xxx.framework.tool.loge
import compose.ScreenMain

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FragmentScreen(
    router: ScreenMain,
    fragmentManager: FragmentManager?,
    modifier: Modifier = Modifier,
) {
    if (fragmentManager == null) {
        throw RuntimeException("if you want use FragmentScreen , fragmentManager must not be null")
    }
    Scaffold(
        modifier = Modifier,
        content = {
            AndroidViewBinding(FragmentScreenBinding::inflate) {
                if (currentRouter != router.route) {
                    showFragment(fragmentManager, router.route)
                }
            }
        }
    )
}

var currentRouter: String? = null

fun showFragment(fragmentManager: FragmentManager, router: String) {
    val fragment = fragmentManager.findFragmentByTag(router)
        ?: (ARouter.getInstance().build(router).navigation())
    if (fragment is Fragment && currentRouter != router) {
        val transaction = fragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            "FragmentScreen showFragment add $router".loge()
            transaction.add(R.id.fl_content, fragment, router)
        } else {
            "FragmentScreen showFragment show $router".loge()
            transaction.show(fragment)
        }
        currentRouter?.let {
            val currentFragment = fragmentManager.findFragmentByTag(currentRouter)
            if (currentFragment != null && currentFragment != fragment) {
                transaction.hide(currentFragment)
                "FragmentScreen showFragment hide $currentRouter".loge()
            }
        }
//        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.commitAllowingStateLoss()
        currentRouter = router
    }
}

