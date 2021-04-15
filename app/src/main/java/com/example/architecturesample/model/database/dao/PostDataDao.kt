package com.example.architecturesample.model.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.example.architecturesample.model.network.response.PostData

@Dao
interface PostDataDao {

    @Query("SELECT * FROM post_data ORDER BY datetime(date) DESC")
    fun findAll() : DataSource.Factory<Int, PostData>
}