package com.api.collegewithadminapp.ui.adminscreens

import android.app.ProgressDialog.show
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.collection.emptyLongSet
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.traceEventEnd
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
import com.api.collegewithadminapp.itemview.BannerItemView
import com.api.collegewithadminapp.itemview.NoticeItemView
import com.api.collegewithadminapp.ui.screens.LoadingDialogue
import com.api.collegewithadminapp.ui.theme.Purple40
import com.api.collegewithadminapp.viewmodel.BannerViewModel
import com.api.collegewithadminapp.viewmodel.NoticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageNotice (navController:NavController) {
    val context = LocalContext.current

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var isNotice by remember {
        mutableStateOf(false)
    }
    var title by remember {
        mutableStateOf("")
    }
    var link by remember {
        mutableStateOf("")
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent() ){
        imageUri = it
    }
    val noticeViewModel : NoticeViewModel = viewModel()
    val isUploaded by noticeViewModel.isPosted.observeAsState(false)
    val isDeleted by noticeViewModel.isDeleted.observeAsState(false)
    val bannerList by noticeViewModel.noticeList.observeAsState(null)

    val showLoader = remember {
        mutableStateOf(false
        )
    }
    if (showLoader.value){
        LoadingDialogue (
            onDismissRequest = {
                showLoader.value = false


            })
    }

    noticeViewModel.getNotice()

    LaunchedEffect(isUploaded) {
        if (isUploaded){
            showLoader.value=false
            Toast.makeText(context, "Notice Uploaded", Toast.LENGTH_SHORT).show()
            imageUri=null
            isNotice=false
        }


    }
    LaunchedEffect(isDeleted) {
        if (isDeleted){
            Toast.makeText(context, "Notice Deleted", Toast.LENGTH_SHORT).show()


        }

    }

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = "Manage Notice",
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
        floatingActionButton = {
            FloatingActionButton(onClick = {
               isNotice = true
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding->
        Column (modifier = Modifier.padding(padding)){

            if (isNotice)
                ElevatedCard(modifier = Modifier.padding(8.dp)) {

                    OutlinedTextField(value = title, onValueChange ={
                        title = it
                    } ,
                        placeholder = { Text(text = "Notice title...")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp))

                    OutlinedTextField(value = link, onValueChange ={
                        link = it
                    } ,
                        placeholder = { Text(text = "Notice link...")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp))


                    Image(painter = if (imageUri==null) painterResource(id = R.drawable.emptyimage)
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
                            if (imageUri==null){
                                Toast.makeText(context, "Please all fields are required", Toast.LENGTH_SHORT).show()
                            }
                            else if (title==""){
                                Toast.makeText(context, "Please all fields are required", Toast.LENGTH_SHORT).show()
                            }

                            else{
                                noticeViewModel.saveNotice(imageUri!!,title,link)
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Add Notice")
                        }
                        OutlinedButton(onClick = { imageUri = null
                                                 isNotice = false},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .weight(1f), colors = ButtonDefaults.buttonColors(Color.Red)
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }

            LazyColumn {
                items(bannerList?: emptyList()){
                    NoticeItemView( it, delete = {docId ->
                        noticeViewModel.deleteNoticd(docId)
                    })

                }

            }
        }



    }


}

    
