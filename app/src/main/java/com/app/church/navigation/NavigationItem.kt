package com.app.church.navigation

import com.app.church.R

sealed class NavigationItem(var route:String, var icon: Int, var title: String){
    data object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    data object Profile : NavigationItem("posts", R.drawable.ic_profile, "Posts")
    data object Settings : NavigationItem("settings", R.drawable.ic_settings, "Settings")
}