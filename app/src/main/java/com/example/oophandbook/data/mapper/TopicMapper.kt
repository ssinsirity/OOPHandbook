package com.example.oophandbook.data.mapper

import com.example.oophandbook.data.db.model.TopicEntity
import com.example.oophandbook.data.db.model.relation.TopicWithUnitsER
import com.example.oophandbook.data.json.model.TopicJson
import com.example.oophandbook.domain.model.Topic

fun TopicWithUnitsER.toUnit() = Topic(
    id = topic.id,
    name = topic.name,
    units = unit.map { it.toUnit() }
)

fun List<TopicWithUnitsER>.toDtos() = map { it.toUnit() }

fun TopicJson.toEntity() = TopicEntity(
    name = name,
    number = number
)

fun TopicEntity.toDto() = Topic(
    id = id,
    name = name,
    units = emptyList()
)