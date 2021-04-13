package com.example.architecturesample.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.architecturesample.model.database.dao.ImageDataDao
import com.example.architecturesample.model.network.response.ImageData
import timber.log.Timber


interface GalleryRepository {
    fun loadGallery(itemId: Int): LiveData<PagingData<ImageData>>
}

class GalleryRepositoryImpl(
    private val dao: ImageDataDao,
) : GalleryRepository {
    override fun loadGallery(itemId: Int): LiveData<PagingData<ImageData>> {
        return Pager(PagingConfig(pageSize = 10, maxSize = 40)) {
            GalleryPagingSource(dao, itemId)
        }.liveData
    }
}

class GalleryPagingSource(
    private val dao: ImageDataDao,
    private val initialKey: Int,
) : PagingSource<Int, ImageData>() {

    override fun getRefreshKey(state: PagingState<Int, ImageData>) =
        state.anchorPosition?.let { anchorPosition ->
            state.closestItemToPosition(anchorPosition)?.id
        }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, ImageData> {
        val key = params.key ?: initialKey
        Timber.d("key=$key")
        val result = when (params) {
            is LoadParams.Refresh ->
                listOf(dao.findInitialImage(key))
            is LoadParams.Prepend ->
                dao.findPrevImages(key, params.loadSize)
            is LoadParams.Append ->
                dao.findNextImages(key, params.loadSize).asReversed()
        }
        return LoadResult.Page(result, result.first().id, result.last().id)
    }
}