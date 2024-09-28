package com.api.collegewithadminapp.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.api.collegewithadminapp.R
import com.api.collegewithadminapp.models.BottomNavItem
import com.api.collegewithadminapp.models.NavItem
import com.api.collegewithadminapp.navigation.Routes
import com.api.collegewithadminapp.ui.theme.Purple40
import kotlinx.coroutines.launch
import okhttp3.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavController) {
    val navController1 = rememberNavController()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }


    val list= listOf(
        NavItem(
            title = "Website",
            iconL = R.drawable.website,
            routes = Routes.AboutUs.route


        ),  NavItem(
            title = "Notice",
            iconL = R.drawable.notice,
            routes = Routes.AboutUs.route
        ),  NavItem(
            title = "Notes",
            iconL = R.drawable.notes,
            routes = Routes.AboutUs.route
        ),  NavItem(
            title = "Contact Us",
            iconL = R.drawable.contact,
            routes = Routes.AboutUs.route
        ),

    )
    ModalNavigationDrawer(
        drawerState=drawerState,
        drawerContent =

        {
            ModalDrawerSheet {
                Column {
                    Box(modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(0.8f)
                        .background(color = Purple40)) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Rounded app icon
                            Image(
                                painter = painterResource(id = R.drawable.applogos), // Replace with your app icon resource
                                contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.secondary)
                                    .padding(8.dp)
                            )
                            // Spacing between icon and app name
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "College With Admin",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    list.forEachIndexed { index, navigationItem ->
                        NavigationDrawerItem(
                            label = { Text(text = navigationItem.title) },
                            selected = index == selectedItemIndex,
                            onClick = {
                                navController.navigate(Routes.AboutUs.route)
                               Toast.makeText(context, navigationItem.title, Toast.LENGTH_SHORT).show()
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = navigationItem.iconL),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)

                                )
                            },

                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                                .fillMaxWidth(0.7f)
                        )
                    }
                }
            }
        },
        content ={
            Scaffold (
                topBar = {
                    TopAppBar(title = { Text(text ="College App" )},
                        navigationIcon ={
                            IconButton(onClick = {scope.launch { drawerState.open() }}) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null
                                )

                                
                            }
                        })
                },
                bottomBar = {
                    MyBottomNav(navController = navController1)

                }

                
            ){ padding ->

                NavHost(navController = navController1, startDestination = Routes.Home.route,
                    modifier = Modifier.padding(padding)) {
                    composable(Routes.Home.route) {
                        Home()
                    }
                    composable(Routes.Faculty.route) {
                        Faculty(navController)
                    }

                    composable(Routes.Gallery.route) {
                        Gallery()
                    }
                    composable(Routes.AboutUs.route) {
                        AboutUs()
                    }





                }



            }

        })


}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MyBottomNav(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val selectedIndex = backStackEntry.value?.destination?.route
    val navigationBarItems = remember{ mutableIntStateOf(0) }

    val list = listOf(
        BottomNavItem(
            "Home",
            R.drawable.home,
            Routes.Home.route
        ),  BottomNavItem(
            "Faculty",
            R.drawable.faculty,
            Routes.Faculty.route
        ),  BottomNavItem(
            "Gallery",
            R.drawable.gallery,
            Routes.Gallery.route
        ),  BottomNavItem(
            "AboutUs",
            R.drawable.user,
            Routes.AboutUs.route
        ),
    )

            BottomAppBar{
                list.forEach {
                    val curRoutes = it.routes
                    val otherRoutes =
                        try {
                            backStackEntry.value?.destination?.route
                        } catch (e: Exception) {
                            Routes.Home.route
                        }
                    val selected = curRoutes == otherRoutes



                    NavigationBarItem(

                        selected = selected, onClick = {
                            navController.navigate(it.routes) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true

                            }
                        }, icon = {
                            Icon(
                                painter = painterResource(id = it.icon),
                                contentDescription = it.title,
                                modifier = Modifier.size(24.dp),
                                tint = if (selected) Color.White else Color.Gray)


                        })
                }

        }
        }






    
