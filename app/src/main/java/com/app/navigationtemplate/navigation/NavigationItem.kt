package com.app.navigationtemplate.navigation

import com.app.navigationtemplate.R

sealed class NavigationItem(var route:String, var icon: Int, var title: String){
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Profile : NavigationItem("profile", R.drawable.ic_profile, "Profile")
    object Settings : NavigationItem("settings", R.drawable.ic_settings, "Settings")
}