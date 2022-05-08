/*
 * Copyright (C) 2021 HyejeanMOON.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.xxx.business.ui.main.R
import com.xxx.common.router.XRouterConfig

data class ScreenMain(
    val route: String,
    @StringRes val stringId: Int,
    @DrawableRes val drawableId: Int
){
    companion object{
        val ScreenHome = ScreenMain(XRouterConfig.FRAGMENT_URL_LOGIN, R.string.screen_home, R.drawable.ic_love)
        val ScreenGame = ScreenMain(XRouterConfig.FRAGMENT_URL_GAME, R.string.screen_game, R.drawable.ic_love)
        val ScreenActivity = ScreenMain(XRouterConfig.FRAGMENT_URL_MINE_SETTING, R.string.screen_activity, R.drawable.ic_love)
        val ScreenFinance = ScreenMain(XRouterConfig.FRAGMENT_URL_FINANCE, R.string.screen_finance, R.drawable.ic_love)
        val ScreenMine = ScreenMain(XRouterConfig.FRAGMENT_URL_MINE, R.string.screen_mine, R.drawable.ic_love)
    }
}
