package com.example.oophandbook.data.mapper

import com.example.oophandbook.data.db.model.relation.BookmarkAndUnitER
import com.example.oophandbook.domain.model.BookmarkedUnit

fun BookmarkAndUnitER.toUnit() = BookmarkedUnit(
    id = bookmark.id,
    unit = unitWithContentER.toBase(),
    topic = unitWithContentER.topic.toDto(),
    bookmarkedAt = bookmark.markedAt
)

fun List<BookmarkAndUnitER>.toDtos() = map { it.toUnit() }