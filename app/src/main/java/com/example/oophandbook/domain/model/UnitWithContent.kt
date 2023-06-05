package com.example.oophandbook.domain.model

data class UnitWithContent(
    val id: Int,
    val topicId: Int,
    val position: Int,
    val name: String,
    val content: List<Content>
)