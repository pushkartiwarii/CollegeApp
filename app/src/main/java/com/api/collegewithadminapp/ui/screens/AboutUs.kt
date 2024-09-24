package com.api.collegewithadminapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.api.collegewithadminapp.viewmodel.CollegeInfoViewModel

@Composable
fun AboutUs () {
    val collegeInfoViewModel : CollegeInfoViewModel = viewModel()
    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)
    collegeInfoViewModel.getCollegeInfo()



    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        if (collegeInfo!= null) {
            
            Image(painter = rememberAsyncImagePainter(model = collegeInfo!!.imageUrl), contentDescription ="college_Image",
                modifier = Modifier.height(220.dp).fillMaxWidth(),
                contentScale = ContentScale.Crop)
            Text(text = collegeInfo!!.name!!,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = collegeInfo!!.desc!!,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = collegeInfo!!.address!!,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = collegeInfo!!.websiteLink!!,
                color = Color.Blue,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
           
        }
    }

    
}