package com.example.architecturesample.di

import com.example.architecturesample.ui.fragment.gallery.GalleryViewModel
import com.example.architecturesample.ui.fragment.imagelist.ImageListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * 뷰모델 관련 모듈 등록 변수 입니다
 */
val viewModelModule = module {
    viewModel { ImageListViewModel(get()) }
    viewModel { GalleryViewModel(get(), get()) }
    /*scope<GalleryFragment>{
        viewModel<GalleryViewModel>()
    }*/
}