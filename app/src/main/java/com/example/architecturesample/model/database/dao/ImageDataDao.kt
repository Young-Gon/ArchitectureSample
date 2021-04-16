package com.example.architecturesample.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.architecturesample.model.database.entity.ImageDataEntity
import com.example.architecturesample.model.network.response.ImageData

@Dao
interface ImageDataDao {
    @Query("SELECT * FROM image_data")
    fun findAll(): LiveData<List<ImageData>>

    @Insert(entity = ImageDataEntity::class)
    suspend fun insertAll(entities: List<ImageData>)

    @Query("DELETE FROM image_data")
    suspend fun deleteAll()

    @Transaction
    suspend fun refreshAll(entities: List<ImageData>){
        deleteAll()
        insertAll(entities)
    }

    @Query("SELECT * FROM image_data WHERE id = :id")
    suspend fun findInitialImage(id: Int): ImageData

    @Query("SELECT * FROM image_data WHERE id < :key ORDER BY id DESC LIMIT :requestedLoadSize")
    suspend fun findNextImages(key: Int, requestedLoadSize: Int): List<ImageData>

    @Query("SELECT * FROM image_data WHERE :key < id ORDER BY id ASC LIMIT :requestedLoadSize")
    suspend fun findPrevImages(key: Int, requestedLoadSize: Int): List<ImageData>
}