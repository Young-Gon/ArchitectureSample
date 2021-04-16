package com.example.architecturesample.model.network.response

import java.time.LocalDateTime

data class PostData(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val modifiedAt: LocalDateTime = LocalDateTime.now(),
)
