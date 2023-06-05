package com.example.oophandbook.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.oophandbook.data.db.model.converter.ContentTypeConverter

@Entity(
    tableName = "Content",
    foreignKeys = [
        ForeignKey(
            entity = UnitEntity::class,
            parentColumns = ["id"],
            childColumns = ["unit_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["unit_id"])
    ]
)
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "unit_id")
    val unitId: Int,

    @ColumnInfo(name = "content_type")
    @TypeConverters(ContentTypeConverter::class)
    val contentType: ContentTypeEntity,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "position")
    val position: Int
)