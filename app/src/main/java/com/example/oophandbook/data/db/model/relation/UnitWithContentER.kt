package com.example.oophandbook.data.db.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.oophandbook.data.db.model.ContentEntity
import com.example.oophandbook.data.db.model.TopicEntity
import com.example.oophandbook.data.db.model.UnitEntity

data class UnitWithContentER(
    @Embedded
    val unit: UnitEntity,

    @Relation(
        parentColumn = "topic_id",
        entityColumn = "id"
    )
    val topic: TopicEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "unit_id"
    )
    val content: List<ContentEntity>
)
