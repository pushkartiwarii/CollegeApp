package com.api.collegewithadminapp.models

data class NavItem(
    val title: String,
    val iconL: Int

)

data class BottomNavItem(
    val title: String,
    val icon: Int,
    val routes: String
)