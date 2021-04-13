package com.example.architecturesample.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.architecturesample.model.database.dao.ImageDataDao
import com.example.architecturesample.model.database.entity.ImageDataEntity


const val DATABASE_NAME = "image_list"

@Database(
    entities = [
        ImageDataEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getImageDataDao(): ImageDataDao
}