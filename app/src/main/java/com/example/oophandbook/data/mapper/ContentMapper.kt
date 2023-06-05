package com.example.oophandbook.data.mapper

import com.example.oophandbook.data.db.model.ContentEntity
import com.example.oophandbook.data.db.model.ContentTypeEntity
import com.example.oophandbook.data.json.model.ContentJson
import com.example.oophandbook.domain.model.Content

fun ContentEntity.toUnit(): Content = when (contentType) {
    ContentTypeEntity.CODE -> Content.Code(
        id = id,
        position = position,
        code = content
    )

    ContentTypeEntity.TEXT -> Content.Text(
        id = id,
        position = position,
        text = content
    )
}

fun List<ContentEntity>.toDtos() = map { it.toUnit() }

fun ContentJson.toEntity(unitId: Int) = ContentEntity(
    unitId = unitId,
    content = data,
    contentType = ContentTypeEntity.from(type),
    position = position
)