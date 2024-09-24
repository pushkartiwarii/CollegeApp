package com.api.collegewithadminapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.api.collegewithadminapp.itemview.NoticeItemView
import com.api.collegewithadminapp.viewmodel.BannerViewModel
import com.api.collegewithadminapp.viewmodel.CollegeInfoViewModel
import com.api.collegewithadminapp.viewmodel.NoticeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home (modifier: Modifier = Modifier) {

    val bannerViewModel : BannerViewModel = viewModel()
    val bannerList by bannerViewModel.bannerList.observeAsState(null)
    bannerViewModel.getBanner()

    val collegeInfoViewModel : CollegeInfoViewModel = viewModel()
    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)
    collegeInfoViewModel.getCollegeInfo()
    
    val noticeViewModel:NoticeViewModel = viewModel()
    val noticeList by noticeViewModel.noticeList.observeAsState(initial = null)
    noticeViewModel.getNotice()

    val pagerState = rememberPagerState(initialPage = 0,)

    val imageSlider = ArrayList<AsyncImagePainter>()

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

    if(bannerList!=null){
    bannerList!!.forEach{
        imageSlider.add(rememberAsyncImagePainter(model = it.url))

    }
    }
    LaunchedEffect (Unit){

        try {
            while (true){
                showLoader.value=false
                yield()
                delay(2600)
                pagerState.animateScrollToPage(page=(pagerState.currentPage+1) % pagerState.pageCount)
            }
        } catch (e: Exception) {
            
        }

    }



    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item{
            com.google.accompanist.pager.HorizontalPager(count = imageSlider.size,
                state = pagerState, modifier = Modifier.padding(4.dp)) {pager->

                Card (modifier=Modifier.height(220.dp)){
                    Image(painter = imageSlider[pager], contentDescription ="banner",

                        Modifier
                            .height(220.dp)
                            .fillMaxWidth(),contentScale = ContentScale.Crop,)
                }


            }

        }
        item{
            Row (horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillParentMaxWidth()){
                HorizontalPagerIndicator(pagerState = pagerState,
                    modifier=Modifier.padding(8.dp))

            }

        }
        item {
            if (collegeInfo!= null) {
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
                Text(text = "Notices",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            }
        items(noticeList?: emptyList()){
            NoticeItemView( it, delete = {docId ->
                noticeViewModel.deleteNoticd(docId)
            })
        }
    }


}