package com.api.collegewithadminapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.api.collegewithadminapp.ui.adminscreens.AdminDashboard
import com.api.collegewithadminapp.ui.adminscreens.FacultyDetailScreen
import com.api.collegewithadminapp.ui.adminscreens.ManageBanner
import com.api.collegewithadminapp.ui.adminscreens.ManageCollegeInfo
import com.api.collegewithadminapp.ui.adminscreens.ManageFaculty
import com.api.collegewithadminapp.ui.adminscreens.ManageGallery
import com.api.collegewithadminapp.ui.adminscreens.ManageNotice
import com.api.collegewithadminapp.ui.screens.AboutUs
import com.api.collegewithadminapp.ui.screens.BottomNav
import com.api.collegewithadminapp.ui.screens.Faculty
import com.api.collegewithadminapp.ui.screens.Gallery
import com.api.collegewithadminapp.ui.screens.Home
import com.api.collegewithadminapp.utilis.constant.isAdmin

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = if (isAdmin) Routes.AdminDashboard.route else Routes.BottomNav.route
    ) {
        composable(Routes.BottomNav.route) {
            BottomNav(navController)
        }
        composable(Routes.Home.route) {
            Home()
        }
        composable(Routes.Faculty.route) {
            Faculty(navController)
        }
        composable(Routes.AboutUs.route) {
            AboutUs()
        }
        composable(Routes.Gallery.route) {
            Gallery()
        }
        composable(Routes.AdminDashboard.route) {
            AdminDashboard(navController)
        }
        composable(Routes.ManageBanner.route) {
            ManageBanner(navController)
        }
        composable(Routes.ManageFaculty.route) {
            ManageFaculty(navController)
        }
        composable(Routes.ManageCollegeInfo.route) {
            ManageCollegeInfo(navController)
        }
        composable(Routes.ManageNotice.route) {
            ManageNotice(navController)
        }
        composable(Routes.ManageGallery.route){
            ManageGallery(navController)

        }
        composable(Routes.FacultyDetailScreen.route){
            val data = it.arguments!!.getString("catName")
            FacultyDetailScreen(navController,data!!)

        }

    }
}