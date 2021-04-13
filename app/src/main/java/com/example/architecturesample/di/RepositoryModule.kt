package com.example.architecturesample.di

import com.example.architecturesample.repository.GalleryRepository
import com.example.architecturesample.repository.GalleryRepositoryImpl
import com.example.architecturesample.repository.ImageListRepository
import com.example.architecturesample.repository.ImageListRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        ImageListRepositoryImpl(get(), get())
    } bind ImageListRepository::class

    factory {
        GalleryRepositoryImpl(get())
    } bind GalleryRepository::class
}