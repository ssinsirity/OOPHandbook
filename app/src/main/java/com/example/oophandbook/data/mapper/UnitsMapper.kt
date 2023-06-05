package com.example.oophandbook.data.mapper

import com.example.oophandbook.data.db.model.UnitEntity
import com.example.oophandbook.data.db.model.relation.UnitWithContentER
import com.example.oophandbook.data.json.model.UnitJson
import com.example.oophandbook.domain.model.UnitBase
import com.example.oophandbook.domain.model.UnitWithContent

fun UnitEntity.toUnit() = UnitBase(
    id = id,
    name = name,
    position = number
)

fun UnitWithContentER.toUnit() = UnitWithContent(
    id = unit.id,
    topicId = unit.topicId,
    position = unit.number,
    name = unit.name,
    content = content.toDtos()
)

fun UnitWithContentER.toBase() = UnitBase(
    id = unit.id,
    name = unit.name,
    position = unit.number
)

fun UnitJson.toEntity(topicId: Int) = UnitEntity(
    topicId = topicId,
    name = name,
    number = number
)