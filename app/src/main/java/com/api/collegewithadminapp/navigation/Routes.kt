package com.api.collegewithadminapp.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")
    object Faculty : Routes("faculty")
    object AboutUs : Routes("about_us")
    object Gallery : Routes("gallery")
    object BottomNav : Routes("bottom_nav")
    object AdminDashboard : Routes ("admin_dashboard")
    object ManageBanner : Routes("manage_banner")
    object ManageFaculty : Routes("manage_faculty")
    object ManageCollegeInfo : Routes("manage_college_info")
    object ManageNotice : Routes("manage_notice")
    object ManageGallery : Routes("manage_gallery")
    object FacultyDetailScreen : Routes("faculty_details/{catName}")

}