package com.example.architecturesample.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "post_data")
data class PostDataEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDateTime,
)
