package com.example.architecturesample.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.architecturesample.model.database.dao.ImageDataDao
import com.example.architecturesample.model.database.dao.PostDataDao
import com.example.architecturesample.model.database.entity.ImageDataEntity
import com.example.architecturesample.model.database.entity.PostDataEntity
import com.example.architecturesample.model.database.typeconverter.LocalDateTypeConverter


const val DATABASE_NAME = "image_list"

@Database(
    entities = [
        ImageDataEntity::class,
        PostDataEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getImageDataDao(): ImageDataDao
    abstract fun getPostDataDao(): PostDataDao
}