package com.example.oophandbook.domain.model

data class BookmarkedUnit(
    val id: Int,
    val topic: Topic,
    val unit: UnitBase,
    val bookmarkedAt: Long = 0
)
