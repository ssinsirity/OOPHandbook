package com.example.oophandbook.data.db.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.oophandbook.data.db.model.TopicEntity
import com.example.oophandbook.data.db.model.UnitEntity

data class TopicWithUnitsER(
    @Embedded
    val topic: TopicEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "topic_id"
    )
    val unit: List<UnitEntity>
)