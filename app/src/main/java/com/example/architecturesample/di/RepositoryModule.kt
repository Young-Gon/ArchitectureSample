package com.example.architecturesample.di

import com.example.architecturesample.repository.*
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        ImageListRepositoryImpl(get(), get())
    } bind ImageListRepository::class

    factory {
        GalleryRepositoryImpl(get())
    } bind GalleryRepository::class

    factory {
        BoardRepositoryImpl(get())
    } bind BoardRepository::class
}