package com.example.nasaapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.*
//
//@Composable
//fun OwlApp(finishActivity: () -> Unit) {
//    ProvideWindowInsets {
//        BlueTheme {
//            val tabs = remember { CourseTabs.values() }
//            val navController = rememberNavController()
//            Scaffold(
//                backgroundColor = MaterialTheme.colors.primarySurface,
//                bottomBar = { OwlBottomBar(navController = navController, tabs) }
//            ) { innerPaddingModifier ->
//                NavGraph(
//                    finishActivity = finishActivity,
//                    navController = navController,
//                    modifier = Modifier.padding(innerPaddingModifier)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun OwlBottomBar(navController: NavController, tabs: Array<CourseTabs>) {
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//        ?: CourseTabs.FEATURED.route
//
//    val routes = remember { CourseTabs.values().map { it.route } }
//    if (currentRoute in routes) {
//        BottomNavigation(
//            Modifier.navigationBarsHeight(additional = 56.dp)
//        ) {
//            tabs.forEach { tab ->
//                BottomNavigationItem(
//                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
//                    label = { Text(stringResource(tab.title).uppercase(Locale.getDefault())) },
//                    selected = currentRoute == tab.route,
//                    onClick = {
//                        if (tab.route != currentRoute) {
//                            navController.navigate(tab.route) {
//                                popUpTo(navController.graph.startDestinationId) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    },
//                    alwaysShowLabel = false,
//                    selectedContentColor = MaterialTheme.colors.secondary,
//                    unselectedContentColor = LocalContentColor.current,
//                    modifier = Modifier.navigationBarsPadding()
//                )
//            }
//        }
//    }
//}
