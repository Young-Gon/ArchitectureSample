package com.example.architecturesample.ui.fragment.imagelist

import androidx.lifecycle.ViewModel
import com.example.architecturesample.repository.ImageListRepository

class ImageListViewModel(
    imageListRepository: ImageListRepository,
):ViewModel() {
    val imageList = imageListRepository.loadImageList()
}