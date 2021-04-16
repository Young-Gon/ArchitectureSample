package com.example.architecturesample.model.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.architecturesample.model.database.entity.PostDataEntity
import com.example.architecturesample.model.network.response.PostData

@Dao
interface PostDataDao {

    @Query("SELECT * FROM post_data ORDER BY datetime(createdAt) DESC")
    fun findAll() : DataSource.Factory<Int, PostData>

    @Query("SELECT * FROM post_data WHERE id = :postId")
    fun findById(postId: Int): LiveData<PostData?>

    @Insert(entity = PostDataEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostData)
}