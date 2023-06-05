package com.example.oophandbook.data.db.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.oophandbook.data.db.model.BookmarkEntity
import com.example.oophandbook.data.db.model.UnitEntity

data class BookmarkAndUnitER(
    @Embedded
    val bookmark: BookmarkEntity,

    @Relation(
        entity = UnitEntity::class,
        parentColumn = "unit_id",
        entityColumn = "id"
    )
    val unitWithContentER: UnitWithContentER
)