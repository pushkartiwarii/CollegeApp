package com.api.collegewithadminapp.ui.adminscreens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.api.collegewithadminapp.itemview.TeacherItemView
import com.api.collegewithadminapp.models.FacultyModel
import com.api.collegewithadminapp.ui.theme.Purple40
import com.api.collegewithadminapp.viewmodel.FacultyViewModel
import com.google.common.net.HttpHeaders.TE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyDetailScreen (navController: NavController,catName:String) {
    val context = LocalContext.current
    val facultyViewModel : FacultyViewModel = viewModel()
    val facultyList by facultyViewModel.facultyList.observeAsState(null)

    facultyViewModel.getFaculty(catName)

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = catName,
                    color = Color.White)
            },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),

                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp()
                    })
                    {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null ,


                            )

                    }
                }

            )

        }
    ) { padding->

        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.padding(padding)) {
            items(facultyList?: emptyList()){
                TeacherItemView(facultyModel = it, delete = {facultyModel->
                    facultyViewModel.deleteFaculty(facultyModel)
                })




            }

        }


    }



}


