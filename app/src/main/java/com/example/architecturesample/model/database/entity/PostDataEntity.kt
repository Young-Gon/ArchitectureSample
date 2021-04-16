package com.example.architecturesample.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "post_data")
data class PostDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime,
)
