package com.example.architecturesample.model.network

import com.example.architecturesample.model.network.response.ImageData
import retrofit2.http.GET

interface ImageAPI {

    @GET("list")
    suspend fun requestImageList(): List<ImageData>
}