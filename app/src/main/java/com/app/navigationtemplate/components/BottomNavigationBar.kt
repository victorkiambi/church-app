package com.app.navigationtemplate.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.navigationtemplate.navigation.NavigationItem
import com.app.navigationtemplate.screens.HomeScreen
import com.app.navigationtemplate.screens.ProfileScreen
import com.app.navigationtemplate.screens.SettingsScreen
import com.app.navigationtemplate.ui.theme.Primary

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Profile,
        NavigationItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    NavigationBar(
        containerColor = Primary,
        contentColor = Color.Black
    ) {
       items.forEachIndexed { index, it ->
           NavigationBarItem(
               icon = { Icon(painter = painterResource(id = it.icon), contentDescription = null)},
               label = { Text(text = it.title) },
               selected = currentRoute == it.route,
               onClick = {
                         navController.navigate(it.route)
                   navController.graph.startDestinationRoute?.let { route ->
                       if (currentRoute != route) {
                           navController.navigate(route) {
                               popUpTo(route) {
                                   saveState = true
                               }
                               launchSingleTop = true
                               restoreState = true
                           }
                       }
                   }
               },
               colors = NavigationBarItemDefaults.colors(
                   selectedIconColor = Color.Black,
                   selectedTextColor = Color.Black,
                   unselectedIconColor = Color.Black,
                   unselectedTextColor = Color.Black,
                   indicatorColor = Color(0xFFCDEF85),
               ),
           )
       }
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavigationItem.Home.route){
        composable(NavigationItem.Home.route){
            HomeScreen()
        }
        composable(NavigationItem.Profile.route){
            ProfileScreen()
        }
        composable(NavigationItem.Settings.route){
            SettingsScreen()
        }
    }
}
@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}