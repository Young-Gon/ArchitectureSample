package com.example.architecturesample.model.network.response

import java.time.LocalDateTime

data class PostData(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDateTime,
)
