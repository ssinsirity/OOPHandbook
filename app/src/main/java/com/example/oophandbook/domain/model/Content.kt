package com.example.oophandbook.domain.model

sealed class Content {
    abstract val id: Int
    abstract val position: Int

    data class Code(
        override val id: Int,
        override val position: Int,
        val code: String,
    ) : Content()

    data class Text(
        override val id: Int,
        override val position: Int,
        val text: String
    ) : Content()
}