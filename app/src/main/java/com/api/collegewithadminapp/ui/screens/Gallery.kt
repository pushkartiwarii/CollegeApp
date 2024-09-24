package com.api.collegewithadminapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.api.collegewithadminapp.itemview.GalleryItemView
import com.api.collegewithadminapp.viewmodel.GalleryViewModel

@Composable
fun Gallery (modifier: Modifier = Modifier) {
    val galleryViewModel : GalleryViewModel = viewModel()
    val galleryList by galleryViewModel.galleryList.observeAsState(null)
    galleryViewModel.getGallery()

    LazyColumn {
        items(galleryList ?: emptyList()) {
            GalleryItemView(it, delete = { docId ->
                galleryViewModel.deleteGallery(docId)
            },deleteImage = { cat,imageUrl->
                galleryViewModel.deleteImage(cat,imageUrl)
            })

        }
    }
}