package com.example.architecturesample.model.database.typeconverter

import androidx.room.TypeConverter
import java.time.LocalDateTime

object LocalDateTypeConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(dateString: String?) = dateString?.let { LocalDateTime.parse(it) }

    @TypeConverter
    @JvmStatic
    fun toDateString(date: LocalDateTime?) = date?.toString()

}