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

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.xxx.business.ui.main.R
import compose.screen.FragmentScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppScaffold(fragmentManager: FragmentManager? = null) {
    val navController = rememberNavController().apply {
        navigatorProvider += remember(this) { AnimatedComposeNavigator() }
    }

//    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var shouldShowBackIcon by remember {
        mutableStateOf(false)
    }

    var shouldShowAppBar by remember {
        mutableStateOf(false)
    }

    val applicationName = "Moonlight Pictures"
    var appBarTitle by remember {
        mutableStateOf(applicationName)
    }

    var currentRoute by remember {
        mutableStateOf(ScreenMain.ScreenMine)
    }
//    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigation(modifier = Modifier.navigationBarsPadding()) {
                val screenList = listOf(
                    ScreenMain.ScreenHome,
                    ScreenMain.ScreenGame,
                    ScreenMain.ScreenActivity,
                    ScreenMain.ScreenFinance,
                    ScreenMain.ScreenMine,
                )
                screenList.forEach { screen ->
                    BottomNavigationItem(
                        selected = currentRoute == screen,
                        onClick = {
                            currentRoute = screen
//                            navController.navigate(route) {
//                                launchSingleTop = true
//                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(28.dp),
                                painter = painterResource(id = screen.drawableId),
                                contentDescription = stringResource(
                                    id = screen.stringId
                                ),
                                tint = if (currentRoute == screen) Color.Red else Color.Black
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = screen.stringId),
                                color = if (currentRoute == screen) Color.Red else Color.Black
                            )
                        }
                    )
                }
            }
        },
        topBar = {
            if (shouldShowAppBar) {
                if (shouldShowBackIcon) {
                    TopAppBar(
                        title = {
                            Text(
                                text = appBarTitle,
                                color = Color.Black
                            )
                        },
                        navigationIcon = {
                            Icon(
                                modifier = Modifier.clickable { navController.back() },
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "back icon",
                                tint = Color.Black
                            )
                        }
                    )
                } else {
                    TopAppBar(
                        title = {
                            Text(
                                text = appBarTitle,
                                color = Color.Black
                            )
                        },
                        actions = {
                            // DropdownMenu
                            DropDownMenuUI()
                        }
                    )
                }
            }
        }
    ) {
        FragmentScreen(currentRoute, fragmentManager)
//        AnimatedNavHost(
//            navController = navController,
//            startDestination = Screen.ScreenHome.route
//        ) {
//            animatedComposable(Screen.ScreenHome.route, this, navBackStackEntry) {
//                FragmentScreen(Screen.ScreenHome.route,fragmentManager)
//                shouldShowAppBar = true
//                shouldShowBackIcon = false
//                appBarTitle =  context.getString(Screen.ScreenHome.stringId)
//            }
//            animatedComposable(Screen.ScreenGame.route, this, navBackStackEntry) {
//                ComposableGame()
//                shouldShowAppBar = false
//                shouldShowBackIcon = false
//                appBarTitle =  context.getString(Screen.ScreenGame.stringId)
//            }
//            animatedComposable(Screen.ScreenActivity.route, this, navBackStackEntry) {
//                FragmentScreen(Screen.ScreenActivity.route,fragmentManager)
//                shouldShowAppBar = false
//                shouldShowBackIcon = false
//                appBarTitle =  context.getString(Screen.ScreenActivity.stringId)
//            }
//            animatedComposable(Screen.ScreenFinance.route, this, navBackStackEntry) {
//                FragmentScreen(Screen.ScreenFinance.route,fragmentManager)
//                shouldShowAppBar = false
//                shouldShowBackIcon = false
//                appBarTitle =  context.getString(Screen.ScreenFinance.stringId)
//            }
//            animatedComposable(Screen.ScreenMine.route, this, navBackStackEntry) {
//                FragmentScreen(Screen.ScreenMine.route,fragmentManager)
//                shouldShowAppBar = false
//                shouldShowBackIcon = false
//                appBarTitle = context.getString(Screen.ScreenMine.stringId)
//            }
//        }
    }
}

//@OptIn(ExperimentalAnimationApi::class)
//fun animatedComposable(
//    route: String,
//    navGraphBuilder: NavGraphBuilder,
//    navBackStackEntry: NavBackStackEntry?,
//    content: @Composable (NavBackStackEntry) -> Unit
//) {
//    navGraphBuilder.composable(
//        route
////        enterTransition = {
////            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(200))
////        },
////        exitTransition = {
////            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(200))
////        },
////        popEnterTransition = {
////            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(200))
////        },
////        popExitTransition = {
////            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(200))
////        }
//    ) {
//        navBackStackEntry?.also {
//            content(it)
//        }
//    }
//}

@Composable
fun DropDownMenuUI() {
    val context = LocalContext.current

    var isExpanded by remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            isExpanded = true
        },
        content = {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More Icon",
                tint = Color.Black
            )
        }
    )

    val list = listOf(
        DropdownMenuItem(
            name = context.getString(R.string.screen_favorites),
            onClick = {
//                FavoritesActivity.start(context)
            }
        )
    )

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false },
        content = {
            Column {
                list.forEachIndexed { _, s ->
                    DropdownMenuItem(onClick = {
                        isExpanded = false
                        s.onClick()
                    }) {
                        Text(text = s.name)
                    }
                }
            }
        }
    )
}

data class DropdownMenuItem(
    val name: String,
    val onClick: () -> Unit
)

fun NavHostController.back() {
    navigateUp()
}