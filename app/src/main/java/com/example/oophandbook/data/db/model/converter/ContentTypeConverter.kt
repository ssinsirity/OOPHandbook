package com.example.oophandbook.data.db.model.converter

import androidx.room.TypeConverter
import com.example.oophandbook.data.db.model.ContentTypeEntity

class ContentTypeConverter {
    @TypeConverter
    fun fromString(value: String) = when(value) {
        "code" -> ContentTypeEntity.CODE
        else -> ContentTypeEntity.TEXT
    }

    @TypeConverter
    fun fromType(value: ContentTypeEntity) = when(value) {
        ContentTypeEntity.CODE -> "code"
        ContentTypeEntity.TEXT -> "text"
    }
}