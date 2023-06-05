package com.example.oophandbook.data.db.model

enum class ContentTypeEntity {
    CODE, TEXT;

    companion object {
        fun from(value: String) = when (value) {
            "code" -> CODE
            else -> TEXT
        }
    }
}