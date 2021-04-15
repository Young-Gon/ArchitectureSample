package com.example.architecturesample.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.architecturesample.model.database.dao.PostDataDao
import com.example.architecturesample.model.network.response.PostData

interface BoardRepository {
    fun getPostList(): LiveData<PagedList<PostData>>
}

class BoardRepositoryImpl(
    private val dao: PostDataDao,
): BoardRepository{
    override fun getPostList() = dao.findAll().toLiveData(pageSize = 50)

}