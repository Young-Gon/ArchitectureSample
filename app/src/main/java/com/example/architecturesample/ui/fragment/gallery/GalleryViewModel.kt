package com.example.architecturesample.ui.fragment.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.architecturesample.repository.GalleryRepository

const val ITEM_ID = "itemId"

class GalleryViewModel(
    private val handle: SavedStateHandle,
    private val galleryRepository: GalleryRepository,
) : ViewModel() {
    val images = galleryRepository.loadGallery(checkNotNull(handle[ITEM_ID]))

    val currentPosition = MutableLiveData(0)
    fun onPageSelected(position: Int) {
        currentPosition.value = position
    }
}