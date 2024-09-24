package com.api.collegewithadminapp.ui.adminscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.api.collegewithadminapp.models.DashboardItemModel
import com.api.collegewithadminapp.navigation.Routes
import com.api.collegewithadminapp.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard(navController: NavController) {

    val list = listOf(
        DashboardItemModel(
            title = "Manage Banner",
            routes = Routes.ManageBanner.route
        ),
        DashboardItemModel(
            title = "Manage Faculty",
            routes = Routes.ManageFaculty.route
        ),
        DashboardItemModel(
            title = "Manage College Info",
            routes = Routes.ManageCollegeInfo.route
        ),
        DashboardItemModel(
            title = "Manage Notice",
            routes = Routes.ManageNotice.route
        ),
        DashboardItemModel(
            title = "Manage Gallery",
            routes = Routes.ManageGallery.route
        ),

    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Admin Dashboard",
                    fontWeight = FontWeight.Bold)
            })

        }, content = {padding->
            LazyColumn(
                modifier = Modifier.padding(padding)) {

                items(items = list , itemContent = {
                    Card(
                        modifier = Modifier.
                        fillMaxWidth()
                            .padding(15.dp)
                            .clickable {
                                navController.navigate(it.routes)
                            }
                    ) {
                        Text(
                            text = it.title,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                            )


                    }

                })

            }
        }
    )



}

@Preview(showSystemUi = true)
@Composable
private fun AdminDashboardPreview() {
    AdminDashboard(navController = rememberNavController())


}