package com.api.collegewithadminapp.ui.adminscreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.api.collegewithadminapp.itemview.TeacherItemView
import com.api.collegewithadminapp.models.FacultyModel
import com.api.collegewithadminapp.navigation.Routes
import com.api.collegewithadminapp.ui.theme.Purple40
import com.api.collegewithadminapp.viewmodel.FacultyViewModel
import com.google.common.net.HttpHeaders.TE

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
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


        FlowRow( modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            maxItemsInEachRow = 2) {
            
                


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(padding)
            ) {

                items(facultyList ?: emptyList()) {

                    TeacherItemView(facultyModel = it, delete = { facultyModel ->
                        facultyViewModel.deleteFaculty(facultyModel)
                    })


                }

            }



    }



}}

@Preview(showSystemUi = true)
@Composable
private fun FacultyDetailScreenPreview() {
    FacultyDetailScreen(navController = NavController(LocalContext.current), catName = "Faculty")

}


