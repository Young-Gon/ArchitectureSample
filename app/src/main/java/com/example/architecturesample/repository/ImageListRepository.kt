package com.example.architecturesample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.architecturesample.model.database.dao.ImageDataDao
import com.example.architecturesample.model.network.ImageAPI
import com.example.architecturesample.model.network.response.ImageData
import com.example.architecturesample.util.NetworkState
import timber.log.Timber

interface ImageListRepository {
    fun loadImageList(): LiveData<NetworkState<List<ImageData>>>
}

class ImageListRepositoryImpl(
    private val dao: ImageDataDao,
    private val api: ImageAPI,
): ImageListRepository {
    override fun loadImageList() = liveData {
        val disposable= emitSource(dao.findAll().map{
            NetworkState.loading(it)
        })

        try {
            val imageList=api.requestImageList()
            disposable.dispose()

            dao.refreshAll(imageList)

            emitSource(dao.findAll().map{
                NetworkState.success(it)
            })
        } catch (e: Exception) {
            Timber.e(e, "ERROR")
            emitSource(dao.findAll().map{
                NetworkState.error(it, e)
            })
        }
    }
}