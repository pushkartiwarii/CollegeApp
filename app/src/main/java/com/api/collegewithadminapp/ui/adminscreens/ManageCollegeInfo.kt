package com.api.collegewithadminapp.ui.adminscreens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.api.collegewithadminapp.R
import com.api.collegewithadminapp.itemview.NoticeItemView
import com.api.collegewithadminapp.models.CollegeInfoModel
import com.api.collegewithadminapp.ui.theme.Purple40
import com.api.collegewithadminapp.viewmodel.CollegeInfoViewModel
import com.api.collegewithadminapp.viewmodel.NoticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCollegeInfo(navController: NavController) {
    val context = LocalContext.current

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var title by remember {
        mutableStateOf("")
    }
    var imageUrl by remember {
        mutableStateOf("")
    }

    var desc by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }
    var link by remember {
        mutableStateOf("")
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ){
        imageUri = it
    }
    val collegeInfoViewModel : CollegeInfoViewModel = viewModel()
    val isUploaded by collegeInfoViewModel.isPosted.observeAsState(false)
    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)

  collegeInfoViewModel.getCollegeInfo()

    LaunchedEffect(isUploaded) {
        if (isUploaded){
            Toast.makeText(context, "College info updated", Toast.LENGTH_SHORT).show()
            imageUri=null
        }


    }
    LaunchedEffect(collegeInfo) {
        if (collegeInfo!=null){
        title = collegeInfo!!.name!!
        link = collegeInfo!!.websiteLink!!
        desc = collegeInfo!!.desc!!
        address = collegeInfo!!.address!!
        address = collegeInfo!!.address!!
        imageUrl = collegeInfo!!.imageUrl!!}

    }


    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = "Manage College Info",
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

        },

    ) { padding->
        Column (modifier = Modifier.padding(padding)){


                ElevatedCard(modifier = Modifier.padding(8.dp)) {

                    OutlinedTextField(value = title, onValueChange ={
                        title = it
                    } ,
                        placeholder = { Text(text = "College Name...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp))
                    OutlinedTextField(value = desc, onValueChange ={
                        desc = it
                    } ,
                        placeholder = { Text(text = "College desc...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp))
                    OutlinedTextField(value = address, onValueChange ={
                        address = it
                    } ,
                        placeholder = { Text(text = "College Address...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp))

                    OutlinedTextField(value = link, onValueChange ={
                        link = it
                    } ,
                        placeholder = { Text(text = "Website Link...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp))


                    Image(painter =if (imageUrl!="") rememberAsyncImagePainter(model = imageUrl)else if (imageUri==null) painterResource(id = R.drawable.emptyimage)
                    else rememberAsyncImagePainter(model=imageUri),contentDescription = "banner_image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                launcher.launch("image/*")

                            }
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Row {
                        Button(onClick = {
                            if (title=="" || desc=="" || address==""){
                                Toast.makeText(context, "Please provide all fields", Toast.LENGTH_SHORT).show()
                            }
                            else if (imageUrl!=""){
                                collegeInfoViewModel.uploadImage(imageUrl ,title,address,desc,link)

                            }
                            else if (imageUri==null){
                                Toast.makeText(context, "Please all fields are required", Toast.LENGTH_SHORT).show()
                            }

                            else{
                                collegeInfoViewModel.saveImage(imageUri!!,title,address,desc,link)
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Update College Info")
                        }

                    }
                }


        }



    }


}





