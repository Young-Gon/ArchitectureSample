package com.example.architecturesample.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.architecturesample.model.database.dao.ImageDataDao
import com.example.architecturesample.model.network.response.ImageData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface GalleryRepository {
    fun loadGallery(itemId:Int,scope: CoroutineScope): LiveData<PagedList<ImageData>>
}

class GalleryRepositoryImpl (
    private val dao: ImageDataDao,
) : GalleryRepository {
    override fun loadGallery(itemId:Int, scope: CoroutineScope) = LivePagedListBuilder(
        ViewPagerDataSource.Factory(
            dao,
            scope,
        ), 10
    ).setInitialLoadKey(itemId).build()
}

class ViewPagerDataSource(
    private val dao: ImageDataDao,
    private val scope: CoroutineScope,
) : ItemKeyedDataSource<Int, ImageData>() {

    class Factory(
        private val dao: ImageDataDao,
        private val viewModelScope: CoroutineScope,
    ) : DataSource.Factory<Int, ImageData>() {
        override fun create(): DataSource<Int, ImageData> {
            return ViewPagerDataSource(dao, viewModelScope)
        }
    }

    override fun getKey(item: ImageData) = item.id

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<ImageData>
    ) {
        scope.launch {
            callback.onResult(listOf(dao.findInitialImage(params.requestedInitialKey ?: 0)))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<ImageData>) {
        scope.launch {
            callback.onResult(dao.findPrevImages(params.key, params.requestedLoadSize))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<ImageData>) {
        scope.launch {
            callback.onResult(dao.findNextImages(params.key, params.requestedLoadSize).asReversed())
        }
    }
}
